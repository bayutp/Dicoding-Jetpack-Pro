package com.example.dicodingjetpackpro

import org.junit.Test

import org.junit.Assert.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class UtilsTest {

    @Test
    fun dateFormat() {
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
        var date :Date? = null
        try {
            date = simpleDateFormat.parse("02/03/2012")
        }catch (e:ParseException){
            e.printStackTrace()
        }

        assertEquals("Fri,03 Feb 2012", date?.let { data ->  Utils.dateFormat(data) })
    }
}