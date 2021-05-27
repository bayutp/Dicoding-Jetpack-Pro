package com.example.dicodingjetpackpro.ui.home

import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseActivity
import com.example.dicodingjetpackpro.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onSetupLayout(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        homeViewModel.getDiscoverMovies()
        homeViewModel.getDiscoverTvs()

        val navController = Navigation.findNavController(this, R.id.home_container)
        NavigationUI.setupWithNavController(_binding.bottomNavigation, navController)
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverMovies().onResult {
                Log.d(TAG, "observeData movies: success > ${Gson().toJsonTree(it)}")
            }

            observeDiscoverTvs().onResult {
                Log.d(TAG, "observeData tv's: success > ${Gson().toJsonTree(it)}")
            }

            observeError().onResult {
                Log.d(TAG, "observeData: error > ${it.error}")
            }

            observeLoading().onResult {
                Log.d(TAG, "observeData: loading > $it")
            }
        }
    }

    companion object {
        private const val TAG = "home"
    }
}
