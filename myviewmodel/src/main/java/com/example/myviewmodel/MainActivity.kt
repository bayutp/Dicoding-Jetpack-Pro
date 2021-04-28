package com.example.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myviewmodel.databinding.ActivityMainBinding
import com.example.myviewmodel.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        displayResult()

        with(_binding) {
            btnCalculate.setOnClickListener {
                val width = edtWidth.text.toString()
                val length = edtLength.text.toString()
                val height = edtHeight.text.toString()

                when {
                    width.isEmpty() -> edtWidth.error = errMsg()
                    length.isEmpty() -> edtLength.error = errMsg()
                    height.isEmpty() -> edtHeight.error = errMsg()
                    else -> {
                        mainViewModel.calculate(width, height, length)
                        displayResult()
                    }
                }
            }

        }
    }

    private fun errMsg() = getString(R.string.err_msg)

    private fun displayResult() {
        _binding.tvResult.text = mainViewModel.result.toString()
    }
}
