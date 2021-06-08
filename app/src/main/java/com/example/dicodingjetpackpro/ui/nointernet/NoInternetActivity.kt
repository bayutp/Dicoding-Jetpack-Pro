package com.example.dicodingjetpackpro.ui.nointernet

import android.os.Bundle
import com.example.dicodingjetpackpro.base.BaseActivity
import com.example.dicodingjetpackpro.databinding.FragmentNoInternetBinding

class NoInternetActivity : BaseActivity() {

    private lateinit var _binding: FragmentNoInternetBinding

    override fun onSetupLayout(savedInstanceState: Bundle?) {
        _binding = FragmentNoInternetBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {}

    override fun observeData() {}
}