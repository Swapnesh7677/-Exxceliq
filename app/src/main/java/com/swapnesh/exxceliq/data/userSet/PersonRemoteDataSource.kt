package com.swapnesh.exxceliq.data.userSet



import com.swapnesh.exxceliq.data.Result
import com.swapnesh.exxceliq.data.remote.BaseDataSource
import com.swapnesh.exxceliq.data.remote.PersonAPI
import com.swapnesh.exxceliq.domain.model.Person
import javax.inject.Inject


class PersonRemoteDataSource @Inject constructor(private val service: PersonAPI) : BaseDataSource() {



    suspend fun  fetchPersonList(page :Int): Result<Person>{
        return  getResult { service.getPersonList(page) }
    }
}
