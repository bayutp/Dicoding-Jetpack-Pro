package com.example.myviewmodel.viewmodel

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun init() {
        mainViewModel = MainViewModel()
    }

    @get:Rule
    var thrown = ExpectedException.none()

    @Test
    fun calculate(){
        val width = "4"
        val length = "4"
        val height = "4"
        mainViewModel.calculate(width, height, length)
        assertEquals(64,mainViewModel.result)
    }

    @Test
    @Throws(NumberFormatException::class)
    fun doubleInputCalculate() {
        val width = "4"
        val length = "4.3"
        val height = "6"
        thrown.expect(NumberFormatException::class.java)
        thrown.expectMessage("For input string: \"4.3\"")
        mainViewModel.calculate(width, height, length)
    }

    @Test
    @Throws(NumberFormatException::class)
    fun emptyInputCalculate() {
        val width = "4"
        val length = ""
        val height = "6"
        thrown.expect(NumberFormatException::class.java)
        thrown.expectMessage("For input string: \"\"")
        mainViewModel.calculate(width, height, length)
    }

}