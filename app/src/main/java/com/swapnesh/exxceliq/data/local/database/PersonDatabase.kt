package com.swapnesh.exxceliq.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.swapnesh.exxceliq.data.local.database.daos.PersonDao

import com.swapnesh.exxceliq.domain.model.PersonData
/**
 * The Room database for this app
 */
@Database(entities = [PersonData::class],
 version = 1, exportSchema = false)
@TypeConverters()
abstract  class PersonDatabase : RoomDatabase() {

  abstract fun getUserList(): PersonDao

  companion object {

   @Volatile
   private var instance: PersonDatabase? = null

   fun getInstance(context: Context): PersonDatabase {
    return instance ?: synchronized(this) {
     instance ?: buildDatabase(context).also { instance = it }
    }
   }

   // Create and pre-populate the database. See this article for more details:
   private fun buildDatabase(context: Context): PersonDatabase {

    return Room.databaseBuilder(context, PersonDatabase::class.java, "person-db")
     .addCallback(object : RoomDatabase.Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
       super.onCreate(db)
      }
     }).build()
   }
  }
}