package com.vangoghscoffee.diamondshare.entities

enum class CreditCardType(val priceAddition: Double) {
    AMERICAN_EXPRESS(0.2),
    MASTERCARD(0.2),
    VISA(0.1)
}