package com.example.dicodingjetpackpro.ui.home

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseActivity
import com.example.dicodingjetpackpro.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onSetupLayout(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(this, R.id.home_container)
        NavigationUI.setupWithNavController(_binding.bottomNavigation, navController)
    }

    override fun observeData() {}
}
