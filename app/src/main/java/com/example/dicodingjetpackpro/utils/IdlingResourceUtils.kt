package com.example.dicodingjetpackpro.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResourceUtils {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        if (!idlingResource.isIdleNow) idlingResource.decrement()
    }
}