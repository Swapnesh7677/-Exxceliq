package com.swapnesh.exxceliq.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapnesh.exxceliq.commonUtil.ConnectivityUtil
import com.swapnesh.exxceliq.data.remote.NetworkState
import com.swapnesh.exxceliq.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mainViewModel : MainViewModel by viewModels()
    private var isConnected : Boolean = true
    var layoutManager: LinearLayoutManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isConnected = ConnectivityUtil.isConnected(this)
        if (!isConnected)
            Toast.makeText(this,"No internet connection!", Toast.LENGTH_SHORT).show()

        layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvpersonList.setHasFixedSize(true)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        binding.rvpersonList.layoutManager = layoutManager

        val adapter = PersonAdapter()
        binding.rvpersonList.adapter = adapter
        subscribeUI(adapter)

    }
    private fun subscribeUI(adapter: PersonAdapter) {
       var data =  mainViewModel.personList(isConnected)
        Log.d("data0",data?.networkState?.value.toString())

        data?.networkState?.observe(this@MainActivity)  {
            Log.d("data0",it.toString())
            when(it) {

                is NetworkState.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.shimmerlayout.visibility = View.VISIBLE
                }
                is NetworkState.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.shimmerlayout.visibility = View.GONE
                    // Handle fail state
                }
                is NetworkState.LOADED -> {
                    binding. progressBar.visibility = View.GONE
                    binding.shimmerlayout.visibility = View.GONE
                }
            }
        }




        data?.pagedList?.observe(this) {
            adapter.submitList(it)
        }

    }

}