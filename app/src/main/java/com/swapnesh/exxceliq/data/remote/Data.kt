package com.swapnesh.exxceliq.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList


data class Data<T>(

    val pagedList: LiveData<PagedList<T>>,

    val networkState: LiveData<NetworkState>)
