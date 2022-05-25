package com.example.kisahnabiapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kisahnabiapp.KisahAdapter
import com.example.kisahnabiapp.R
import com.example.kisahnabiapp.data.KisahResponse
import com.example.kisahnabiapp.databinding.ActivityMainBinding
import com.example.kisahnabiapp.utils.OnItemClickCallback

class MainActivity : AppCompatActivity(), OnItemClickCallback {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getData()

        viewModel.kisahResponse.observe(this) { showData(it) }
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.isError.observe(this) { showError(it) }
    }

    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = KisahAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(item: KisahResponse) {
                    startActivity(
                        Intent(
                            applicationContext,
                            DetailActivity::class.java
                        ).putExtra(DetailActivity.EXTRA_DATA, item)
                    )
                }
            })
        }
        binding.recyclerMain.adapter
    }

    private fun showLoading(loading: Boolean?) {
        if (loading == true) {
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility = View.INVISIBLE
        } else {
            binding.progressMain.visibility = View.INVISIBLE
            binding.recyclerMain.visibility = View.VISIBLE
        }
    }

    private fun showError(error: Throwable) {
        Log.e("MainActivity", "showError $error")
    }

    override fun onItemClicked(item: KisahResponse) {

    }
}