package com.example.dicodingjetpackpro.viewmodel

import com.example.dicodingjetpackpro.model.CuboidModel

class CuboidViewModel(private val cuboidModel: CuboidModel) {
    fun getCircumference() = cuboidModel.getCircumference()
    fun getSurfaceArea() = cuboidModel.getSurfaceAre()
    fun getVolume() = cuboidModel.getVolume()
    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(length = l, width = w, height = h)
    }
}