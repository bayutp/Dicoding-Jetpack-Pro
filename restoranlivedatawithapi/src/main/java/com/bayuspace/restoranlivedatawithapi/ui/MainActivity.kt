package com.bayuspace.restoranlivedatawithapi.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bayuspace.restoranlivedatawithapi.R
import com.bayuspace.restoranlivedatawithapi.databinding.ActivityMainBinding
import com.bayuspace.restoranlivedatawithapi.ui.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        supportActionBar?.hide()
        _binding.apply {
            btnSend.setOnClickListener {
                if (edReview.text?.isNotEmpty() == true) {
                    viewModel.postReview(edReview.text.toString())
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                } else edReview.error = "Field must not empty"
            }
        }
        viewModel.restaurant.observe(this, {
            _binding.apply {
                tvTitle.text = it.name
                tvDescription.text = it.description
                Glide.with(this@MainActivity)
                    .load("https://restaurant-api.dicoding.dev/images/large/${it.pictureId}")
                    .into(ivPicture)
            }
        })

        viewModel.listReview.observe(this, { consumerReviews ->
            val listReview = consumerReviews.map {
                "${it.review}\n- ${it.name}"
            }
            _binding.apply {
                lvReview.adapter = ArrayAdapter(this@MainActivity, R.layout.item_review, listReview)
                edReview.setText("")
            }
        })

        viewModel.isLoading.observe(this, {
            _binding.apply {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.error.observe(this, {
            Toast.makeText(this@MainActivity, "Error : $it", Toast.LENGTH_SHORT).show()
        })

        viewModel.textSnackbar.observe(this, {
            it.getContentIfNotHandled()?.let { msg ->
                Snackbar.make(window.decorView.rootView, msg, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}