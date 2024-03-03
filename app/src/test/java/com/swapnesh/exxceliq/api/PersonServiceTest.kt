package com.swapnesh.exxceliq.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.swapnesh.exxceliq.data.remote.PersonAPI
import com.swapnesh.exxceliq.utils.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class PersonServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: PersonAPI
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(Constants.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PersonAPI::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestPerson() {
        runBlocking {
            enqueueResponse("person.json")
            val resultResponse = service.getPersonList(1).body()


            assertNotNull(resultResponse)

        }
    }

    @Test
    fun getPersonResponse() {
        runBlocking {
            enqueueResponse("person.json")
            val resultResponse = service.getPersonList(1).body()
            val newsList = resultResponse?.data

            assertThat(resultResponse?.data?.size, `is`(6))
            assertThat(newsList?.size, `is`(6))
        }
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("api-response/$fileName")
        val source = inputStream?.let { it.source().buffer() }
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        if (source != null) {
            mockWebServer.enqueue(mockResponse.setBody(
                source.readString(Charsets.UTF_8))
            )
        }
    }
}


