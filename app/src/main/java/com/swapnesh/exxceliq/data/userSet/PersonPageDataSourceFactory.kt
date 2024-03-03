package com.swapnesh.exxceliq.data.userSet

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.swapnesh.exxceliq.data.local.database.daos.PersonDao

import com.swapnesh.exxceliq.domain.model.PersonData

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PersonPageDataSourceFactory @Inject constructor(
    private val dataSource: PersonRemoteDataSource,
    private val dao: PersonDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, PersonData>() {

    val liveData = MutableLiveData<PersonPageDataSource>()

    override fun create(): DataSource<Int, PersonData> {
        val source = PersonPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 6
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}
