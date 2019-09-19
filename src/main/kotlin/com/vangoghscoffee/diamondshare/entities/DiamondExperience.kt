package com.vangoghscoffee.diamondshare.entities

data class DiamondExperience(
        val selectedCar: Car,
        val driver: Driver,
        var fare: Fare
) {
}