package com.example.dicodingjetpackpro.ui.home

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseActivity
import com.example.dicodingjetpackpro.databinding.ActivityMainBinding
import com.example.dicodingjetpackpro.utils.gone
import com.example.dicodingjetpackpro.utils.visible

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null

    override fun onSetupLayout(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(this, R.id.home_container)
        NavigationUI.setupWithNavController(_binding!!.bottomNavigation, navController)
    }

    override fun observeData() {}

    fun hideBottomNavigation(state: Boolean = true) {
        if (state) _binding?.bottomNavigation?.gone() else _binding?.bottomNavigation?.visible()
    }
}
