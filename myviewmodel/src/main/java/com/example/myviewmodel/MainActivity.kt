package com.example.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myviewmodel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        displayResult()

        btn_calculate.setOnClickListener {
            val width = edt_width.text.toString()
            val length = edt_length.text.toString()
            val height = edt_height.text.toString()

            when{
                width.isEmpty() -> edt_width.error = "Masih kosong"
                length.isEmpty() -> edt_length.error = "Masih kosong"
                height.isEmpty() -> edt_height.error = "Masih kosong"
                else -> {
                    mainViewModel.calculate(width, height, length)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult(){
        tv_result.text = mainViewModel.result.toString()
    }
}
