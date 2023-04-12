package com.ezatpanah.mvi_themealdb_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ezatpanah.mvi_themealdb_api.R
import com.ezatpanah.mvi_themealdb_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}