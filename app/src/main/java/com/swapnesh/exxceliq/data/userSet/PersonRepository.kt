package com.swapnesh.exxceliq.data.userSet

import androidx.annotation.OpenForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder


import com.swapnesh.exxceliq.data.local.database.daos.PersonDao
import com.swapnesh.exxceliq.data.remote.Data
import com.swapnesh.exxceliq.data.remote.NetworkState
import com.swapnesh.exxceliq.domain.model.Person
import com.swapnesh.exxceliq.domain.model.PersonData

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class PersonRepository @Inject constructor(
    private val personDao: PersonDao,
    private val personRemoteDataSource: PersonRemoteDataSource
) {

     fun observePersonlist(connectivityAvailable : Boolean, coroutineScope: CoroutineScope)
            : Data<PersonData> {

        return if (connectivityAvailable)
            observeRemotePagedPerson(coroutineScope)
        else observeLocalPagedPerson()
    }
    private  fun observeLocalPagedPerson(): Data<PersonData> {

        val dataSourceFactory = personDao.getPerson()

        val createLD = MutableLiveData<NetworkState>()
        createLD.postValue(NetworkState.LOADED)

        return Data(LivePagedListBuilder(dataSourceFactory,
            PersonPageDataSourceFactory.pagedListConfig()).build(),createLD)
    }



    private fun observeRemotePagedPerson(ioCoroutineScope: CoroutineScope): Data<PersonData> {
        val dataSourceFactory = PersonPageDataSourceFactory(personRemoteDataSource,
            personDao, ioCoroutineScope)
        val networkState = dataSourceFactory.liveData.switchMap {
            it.networkState
        }
        return Data(LivePagedListBuilder(dataSourceFactory,
            PersonPageDataSourceFactory.pagedListConfig()
        ).build(),networkState)
    }
}
