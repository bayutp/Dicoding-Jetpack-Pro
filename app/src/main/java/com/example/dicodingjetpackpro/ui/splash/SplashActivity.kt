package com.example.dicodingjetpackpro.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dicodingjetpackpro.databinding.ActivityMainBinding
import com.example.dicodingjetpackpro.databinding.ActivitySplashBinding
import com.example.dicodingjetpackpro.ui.home.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            delay(6000L)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}