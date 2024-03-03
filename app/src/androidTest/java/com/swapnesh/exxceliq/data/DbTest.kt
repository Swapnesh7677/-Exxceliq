package com.swapnesh.exxceliq.data


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.swapnesh.exxceliq.data.local.database.PersonDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

abstract class DbTest {
  //  @Rule
   // @JvmField
  //  val countingTaskExecutorRule =CountingTaskExecutorRule()



    private lateinit var _db:PersonDatabase
    val db: PersonDatabase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PersonDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
      //  countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        _db.close()
    }
}
