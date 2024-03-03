package com.swapnesh.exxceliq.data.userSet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.swapnesh.exxceliq.data.Result
import com.swapnesh.exxceliq.data.local.database.daos.PersonDao
import com.swapnesh.exxceliq.data.remote.NetworkState
import com.swapnesh.exxceliq.domain.model.Person
import com.swapnesh.exxceliq.domain.model.PersonData

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonPageDataSource @Inject constructor(
    private val remoteDataSource: PersonRemoteDataSource,
    private val personDao: PersonDao,
    private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Int, PersonData>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PersonData>
    ) {
        networkState.postValue(NetworkState.LOADING)
        fetchData(page = 1 ){
            callback.onResult(it, null, 2)
        }
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PersonData>) {
        networkState.postValue(NetworkState.LOADING)
        val page = params.key
        fetchData(page = page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PersonData>) {
        val page = params.key
        fetchData(page,) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<PersonData>) -> Unit) {
        coroutineScope.launch(getJobErrorHandler()) {
            when (val response = remoteDataSource.fetchPersonList( page)) {

                is Result.Error -> {
                    networkState.postValue(NetworkState.ERROR(response.message ?: "Unknown error"))
                    postError(response.message)
                }
                is Result.Success -> {
                    val results = response.data
                    for (result in results.data) {
                        personDao.insert(result)
                    }
                    callback(results.data)
                    networkState.postValue(NetworkState.LOADED)
                }
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String?) {
        Log.e("NewsPageDataSource","An error happened: $message")
    }
}
