package com.example.dicodingjetpackpro.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment() {

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseFragment, Observer { data -> data?.let(action) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideToolbar()
        onViewReady(savedInstanceState)
        observeData()
    }

    protected abstract fun onViewReady(savedInstanceState: Bundle?)
    protected abstract fun observeData()

    protected fun hideToolbar(isHide: Boolean = false) {
        (activity as AppCompatActivity).supportActionBar?.let {
            if (isHide) {
                it.hide()
            } else {
                it.show()
            }
        }
    }
}