package com.example.kisahnabiapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kisahnabiapp.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getStringExtra(EXTRA_DATA)
    }
    companion object {
        val EXTRA_DATA = "EXTRA_DATA"
    }
}