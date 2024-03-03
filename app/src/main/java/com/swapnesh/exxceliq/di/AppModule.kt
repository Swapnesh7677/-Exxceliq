package com.swapnesh.exxceliq.di

import android.app.Application
import com.swapnesh.exxceliq.data.local.database.PersonDatabase
import com.swapnesh.exxceliq.data.remote.PersonAPI
import com.swapnesh.exxceliq.data.remote.RetrofitFactory
import com.swapnesh.exxceliq.data.userSet.PersonRemoteDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesPersonService(): PersonAPI = RetrofitFactory.getPersonService()

    @Singleton
    @Provides
    fun providePersonRemoteDataSource(personService: PersonAPI)
            = PersonRemoteDataSource(personService)

    @Singleton
    @Provides
    fun provideDb(app: Application) = PersonDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsSetDao(db: PersonDatabase) = db.getUserList()
}
