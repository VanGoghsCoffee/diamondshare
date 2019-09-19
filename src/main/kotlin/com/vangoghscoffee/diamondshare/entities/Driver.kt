package com.vangoghscoffee.diamondshare.entities

data class Driver(
        val userName: String,
        val creditCard: CreditCardType,
        val device: Device
) {
}