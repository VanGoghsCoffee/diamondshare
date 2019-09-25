package com.vangoghscoffee.diamondshare.service

import com.vangoghscoffee.diamondshare.configuration.DiamondShareConfiguration
import com.vangoghscoffee.diamondshare.entities.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import kotlin.properties.Delegates

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [DiamondShareConfiguration::class])
class TestDriverRules {

    @Autowired
    lateinit var diamondExperienceService: DiamondExperienceService

    var selectedCar: Car by Delegates.notNull()
    var fare: Fare by Delegates.notNull()
    var allCars: List<Car> by Delegates.notNull()

    @Before
    fun `set up tests`() {
        selectedCar = Car(Coordinate(0.0, 0.0), CarType.PORSCHE, 4)
        fare = Fare(0.0, 0.0,0.0,0.0,0.0, 0.0)

        allCars = listOf(
                Car(Coordinate(0.4, 0.4), CarType.PORSCHE, 2),
                Car(Coordinate(0.21, 0.23), CarType.PORSCHE, 4),
                Car(Coordinate(0.3, 0.23), CarType.DEFENDER, 4),
                Car(Coordinate(0.23, 0.21), CarType.DEFENDER, 9)
        )
    }

    @Test
    fun `when driver device OS is of type Mac then set device surcharge to 40 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.MAC))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.2, finalExperience.fare.deviceSurcharge, 0.0)
    }

    @Test
    fun `when driver device OS is of type IOS then set device surcharge to 40 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.IOS))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.3, finalExperience.fare.deviceSurcharge, 0.0)
    }

    @Test
    fun `when driver device OS is of type Windows then set device surcharge to 10 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.WINDOWS))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.1, finalExperience.fare.deviceSurcharge, 0.0)
    }

    @Test
    fun `when driver device OS is of type Android then set device surcharge to 10 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.ANDROID))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.2, finalExperience.fare.deviceSurcharge, 0.0)
    }

    @Test
    fun `when driver device OS is of type Linux then set device surcharge to 0 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.LINUX))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.0, finalExperience.fare.deviceSurcharge, 0.0)
    }

    @Test
    fun `when driver credit card type is visa then set credit card surcharge to 10 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.LINUX))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.0, finalExperience.fare.creditCardSurcharge, 0.1)
    }

    @Test
    fun `when driver credit card type is mastercard then set credit card surcharge to 20 cents`() {
        val driver = Driver("David", CreditCardType.MASTERCARD, Device("UserAgent", OSType.LINUX))

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.0, finalExperience.fare.creditCardSurcharge, 0.2)
    }
}