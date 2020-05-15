package com.example.dicodingjetpackpro.model

class CuboidModel {
    private var width = 0.0
    private var height = 0.0
    private var length = 0.0

    fun getVolume() = length * width * height

    fun getSurfaceAre(): Double {
        val wl = width * length
        val wh = width * height
        val lh = length * height
        return 2 * (wl + wh + lh)
    }

    fun getCircumference() = 4 * ( length + width + height)

    fun save(width:Double, height:Double, length:Double){
        this.width = width
        this.height = height
        this.length= length
    }
}