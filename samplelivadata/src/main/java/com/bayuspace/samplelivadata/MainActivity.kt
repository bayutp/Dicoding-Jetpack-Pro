package com.bayuspace.samplelivadata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.samplelivadata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(_binding.root)
        subscribe()
    }

    private fun subscribe() {
        val observer = Observer<Long?> {
            val value = this@MainActivity.resources.getString(R.string.seconds, it)
            _binding.timerTextView.text = value
        }

        viewModel.getElapseTime().observe(this, observer)
    }
}
