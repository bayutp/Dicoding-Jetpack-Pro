package com.example.dicodingjetpackpro

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dicodingjetpackpro.databinding.ActivityMainBinding
import com.example.dicodingjetpackpro.model.CuboidModel
import com.example.dicodingjetpackpro.viewmodel.CuboidViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: CuboidViewModel
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //declare view model
        viewModel = CuboidViewModel(CuboidModel())

        //setup listener
        with(_binding) {
            btnSave.setOnClickListener(this@MainActivity)
            btnCalculateCircumference.setOnClickListener(this@MainActivity)
            btnCalculateSurfaceArea.setOnClickListener(this@MainActivity)
            btnCalculateVolume.setOnClickListener(this@MainActivity)
        }

    }

    override fun onClick(v: View) {
        with(_binding) {
            val length = edtLength.text.toString().trim()
            val width = edtWidth.text.toString().trim()
            val height = edtHeight.text.toString().trim()
            val errorMsg = "Field ini tidak boleh kosong"

            when {
                length.isEmpty() -> edtLength.error = errorMsg
                width.isEmpty() -> edtWidth.error = errorMsg
                height.isEmpty() -> edtHeight.error = errorMsg
                else -> {
                    val l = length.toDouble()
                    val w = width.toDouble()
                    val h = height.toDouble()

                    when (v.id) {
                        R.id.btn_save -> {
                            viewModel.save(l, w, h)
                            visible()
                        }
                        R.id.btn_calculate_surface_area -> {
                            tvResult.text = viewModel.getSurfaceArea().toString()
                            gone()
                        }
                        R.id.btn_calculate_circumference -> {
                            tvResult.text = viewModel.getCircumference().toString()
                            gone()
                        }
                        R.id.btn_calculate_volume -> {
                            tvResult.text = viewModel.getVolume().toString()
                            gone()
                        }
                    }
                }
            }
        }
    }

    private fun visible() {
        with(_binding) {
            btnCalculateVolume.visibility = View.VISIBLE
            btnCalculateCircumference.visibility = View.VISIBLE
            btnCalculateSurfaceArea.visibility = View.VISIBLE
            btnSave.visibility = View.GONE
        }
    }

    private fun gone() {
        with(_binding) {
            btnCalculateVolume.visibility = View.GONE
            btnCalculateCircumference.visibility = View.GONE
            btnCalculateSurfaceArea.visibility = View.GONE
            btnSave.visibility = View.VISIBLE
        }
    }
}
