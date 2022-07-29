package com.mmrbd.hilt_mvvm_retrofit_coroutine.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmrbd.hilt_mvvm_retrofit_coroutine.R
import com.mmrbd.hilt_mvvm_retrofit_coroutine.databinding.ActivityMainBinding
import com.mmrbd.hilt_mvvm_retrofit_coroutine.ui.adapters.UserViewAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var userViewAdapter: UserViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar.apply { title = getString(R.string.home) }

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rcv.layoutManager = layoutManager
        userViewAdapter = UserViewAdapter(this) {
            Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
        }
        val dividerItemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        binding.rcv.addItemDecoration(dividerItemDecoration)
        binding.rcv.adapter = userViewAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.liveDataListObserver()
            .observe(this) {
                if (it != null) {
                    userViewAdapter.setDataList(it)
                } else {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG)
                }
            }
        viewModel.loadLiveUserDataList("md")
    }
}