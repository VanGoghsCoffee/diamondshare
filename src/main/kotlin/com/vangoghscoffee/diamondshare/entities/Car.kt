package com.vangoghscoffee.diamondshare.entities

data class Car(
        val location: Coordinate,
        val type: CarType,
        val seats: Int
) {
}