package com.example.dicodingjetpackpro.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseActivity : AppCompatActivity() {

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseActivity, Observer { data -> data?.let(action) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetupLayout(savedInstanceState)
        onViewReady(savedInstanceState)
        observeData()
    }

    protected abstract fun onSetupLayout(savedInstanceState: Bundle?)
    protected abstract fun onViewReady(savedInstanceState: Bundle?)
    protected abstract fun observeData()
}