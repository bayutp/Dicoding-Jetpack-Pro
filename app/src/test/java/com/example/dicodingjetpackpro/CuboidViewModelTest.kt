package com.example.dicodingjetpackpro

import com.example.dicodingjetpackpro.model.CuboidModel
import com.example.dicodingjetpackpro.viewmodel.CuboidViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*

class CuboidViewModelTest {

    private lateinit var viewmodel:CuboidViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dummyLength = 5.0
    private val dummyWidth = 4.0
    private val dummyHeight = 3.0

    private val dummyVolume = 60.0
    private val dummyCircumference = 48.0
    private val dummySurfaceArea = 94.0
    @Before
    fun before(){
        cuboidModel = mock(CuboidModel::class.java)
        viewmodel = CuboidViewModel(cuboidModel)
    }

    @Test
    fun getCircumference() {
        cuboidModel = CuboidModel()
        viewmodel = CuboidViewModel(cuboidModel)
        viewmodel.save(l = dummyLength, w = dummyWidth, h = dummyHeight)
        val circumference = viewmodel.getCircumference()
        assertEquals(dummyCircumference,circumference,0.0001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        viewmodel = CuboidViewModel(cuboidModel)
        viewmodel.save(l = dummyLength, w = dummyWidth, h = dummyHeight)
        val surfaceArea = viewmodel.getSurfaceArea()
        assertEquals(dummySurfaceArea,surfaceArea,0.0001)
    }

    @Test
    fun getVolume() {
        cuboidModel = CuboidModel()
        viewmodel = CuboidViewModel(cuboidModel)
        viewmodel.save(l = dummyLength, w = dummyWidth, h = dummyHeight)
        val volume = viewmodel.getVolume()
        assertEquals(dummyVolume,volume,0.0001)
    }

    @Test
    fun mockitoVolume(){
        `when`(viewmodel.getVolume()).thenReturn(dummyVolume)
        val volume = viewmodel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dummyVolume,volume,0.0001)
    }

    @Test
    fun mockitoSurfaceArea(){
        `when`(viewmodel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surfaceArea = viewmodel.getSurfaceArea()
        verify(cuboidModel).getSurfaceAre()
        assertEquals(dummySurfaceArea,surfaceArea,0.0001)
    }

    @Test
    fun mockitoCircumference() {
        `when`(viewmodel.getCircumference()).thenReturn(dummyCircumference)
        val circumference = viewmodel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(dummyCircumference, circumference, 0.0001)
    }

    @Test
    fun save() {
    }
}