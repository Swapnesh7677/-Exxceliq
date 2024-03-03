package com.swapnesh.exxceliq.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.swapnesh.exxceliq.data.remote.Data
import com.swapnesh.exxceliq.data.userSet.PersonRepository
import com.swapnesh.exxceliq.domain.model.Person
import com.swapnesh.exxceliq.domain.model.PersonData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: PersonRepository,) :ViewModel() {
    private var personList: Data<PersonData>? = null

    private val ioCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    fun personList(connectivityAvailable: Boolean): Data<PersonData>? {

        if (personList == null) {
            personList = repository.observePersonlist(connectivityAvailable, ioCoroutineScope)
        }
        return personList
    }

    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

}