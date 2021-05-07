package com.bayuspace.samplelivadata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {
    companion object {
        const val ONE_SECOND = 1_000
    }

    private val mInitialTime = SystemClock.elapsedRealtime()
    private val mElapseTime = MutableLiveData<Long?>()

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1_000
                mElapseTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    fun getElapseTime(): LiveData<Long?> {
        return mElapseTime
    }
}