package com.vangoghscoffee.diamondshare.entities

import kotlin.math.sqrt
import kotlin.math.cos

class Coordinate(
        val latitude: Double,
        val longitude: Double
) {
    fun distanceTo(coordinate: Coordinate): Double {
        val r = 6371
        val x = (this.longitude - coordinate.longitude) * cos((coordinate.latitude + this.latitude) / 2)
        val y = (this.latitude - coordinate.latitude)
        val distance = sqrt(x * x + y * y) * r
        println("distance: $distance")
        return distance;
    }
}