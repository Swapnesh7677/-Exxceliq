package com.swapnesh.exxceliq.data.local.database.daos

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


import com.swapnesh.exxceliq.domain.model.PersonData

@Dao
interface PersonDao {

    @Query("Select * from PersonData")
    fun getListPerson() : LiveData<List<PersonData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(items: PersonData)


    @Query("SELECT * FROM PersonData")
    fun getPerson(): DataSource.Factory<Int, PersonData>
}