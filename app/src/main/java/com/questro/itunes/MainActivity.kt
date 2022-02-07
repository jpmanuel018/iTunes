package com.questro.itunes

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.questro.itunes.presentation.music_list.MusicInfoUIState
import com.questro.itunes.presentation.music_list.MusicListAdapter
import com.questro.itunes.presentation.music_list.MusicListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MusicListAdapter
    private lateinit var searchInput: TextInputEditText
    private lateinit var progressBar: CircularProgressIndicator

    private var searchJob: Job? = null

    private val viewModel: MusicListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progress_circular)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MusicListAdapter(this)
        recyclerView.adapter = adapter

        searchInput = findViewById(R.id.searchInput)
        searchInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(isNetworkAvailable()){
                    searchJob?.cancel()
                    searchJob = CoroutineScope(Dispatchers.Main).launch {
                        if(s.toString().isNotEmpty()){
                            delay(500)
                            viewModel.getInfo(s.toString())
                        }else{
                            adapter.clearItems()
                        }
                    }
                }else{
                    Toast.makeText(applicationContext,"No Internet Connection",Toast.LENGTH_SHORT).show()
                }
            }

        })

        lifecycleScope.launchWhenStarted {
            viewModel.scanResultUIState.collect {
                when(it){
                    is MusicInfoUIState.Loading -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is MusicInfoUIState.Success -> {
                        adapter.setItems(it.items)
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    is MusicInfoUIState.Error -> {
                        Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun isNetworkAvailable():Boolean{
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        return internetInfo!=null && internetInfo.isConnected
    }
}