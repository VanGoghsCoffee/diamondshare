package com.vangoghscoffee.diamondshare.entities

data class Fare(
    var finalPrice: Double,
    var deviceSurcharge: Double,
    var creditCardSurcharge: Double,
    var carSurcharge: Double,
    var locationSurcharge: Double,
    var seatSurcharge: Double
) {}