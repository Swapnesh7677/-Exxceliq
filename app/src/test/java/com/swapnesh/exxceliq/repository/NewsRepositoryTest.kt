package com.swapnesh.exxceliq.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.swapnesh.exxceliq.data.local.database.PersonDatabase
import com.swapnesh.exxceliq.data.local.database.daos.PersonDao
import com.swapnesh.exxceliq.data.remote.PersonAPI
import com.swapnesh.exxceliq.data.userSet.PersonRemoteDataSource
import com.swapnesh.exxceliq.data.userSet.PersonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class NewsRepositoryTest {

    private lateinit var repository: PersonRepository
    private val dao = mock(PersonDao::class.java)
    private val service = mock(PersonAPI::class.java)
    private val remoteDataSource = PersonRemoteDataSource(service)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        val db = mock(PersonDatabase::class.java)
        `when`(db.getUserList()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = PersonRepository(dao, remoteDataSource)
    }

    @Test
    fun loadNewsFromNetwork() {
        runBlocking {
            repository.observePersonlist(connectivityAvailable = true,
                     coroutineScope = coroutineScope)

            verify(dao, never()).getListPerson()
            verifyZeroInteractions(dao)
        }
    }
}
