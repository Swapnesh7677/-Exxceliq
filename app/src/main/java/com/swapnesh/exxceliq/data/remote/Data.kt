package com.swapnesh.exxceliq.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class Data<T>(

    val pagedList: LiveData<PagedList<T>>,

    val networkState: LiveData<NetworkState>)
