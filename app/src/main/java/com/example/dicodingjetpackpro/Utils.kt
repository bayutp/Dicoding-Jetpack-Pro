package com.example.dicodingjetpackpro

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    @SuppressLint("SimpleDateFormat")
    fun dateFormat(data: Date):String{
        return SimpleDateFormat("EEE,dd MMM YYYY").format(data)
    }
}