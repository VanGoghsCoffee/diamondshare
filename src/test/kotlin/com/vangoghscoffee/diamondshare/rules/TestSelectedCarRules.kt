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
class TestSelectedCarRules {

    @Autowired
    lateinit var diamondExperienceService: DiamondExperienceService

    var device: Device by Delegates.notNull()
    var driver: Driver by Delegates.notNull()
    var fare: Fare by Delegates.notNull()
    var allCars: List<Car> by Delegates.notNull()

    @Before
    fun `set up tests`() {
        device = Device("UserAgent", OSType.MAC)
        driver = Driver("David", CreditCardType.VISA, device)
        fare = Fare(0.0, 0.0,0.0,0.0,0.0, 0.0)

        allCars = listOf(
                Car(Coordinate(0.4, 0.4), CarType.PORSCHE, 2),
                Car(Coordinate(0.21, 0.23), CarType.PORSCHE, 4),
                Car(Coordinate(0.3, 0.23), CarType.DEFENDER, 4),
                Car(Coordinate(0.23, 0.21), CarType.DEFENDER, 9)
        )
    }

    @Test
    fun `when selected car has more than 4 seats then set seat surcharge to 20 cents`() {
        val selectedCar = Car(Coordinate(0.2, 0.2), CarType.DEFENDER, 6)

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.2, finalExperience.fare.seatSurcharge, 0.0)
    }

    @Test
    fun `when selected car is of type Porsche then set car surcharge to 40 cents`() {
        val selectedCar = Car(Coordinate(0.2, 0.2), CarType.PORSCHE, 4)

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.6, finalExperience.fare.carSurcharge, 0.0)
    }

    @Test
    fun `when selected car is of type Defender then set car surchrage to 20 cents`() {
        val selectedCar = Car(Coordinate(0.2, 0.2), CarType.DEFENDER, 4)

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.42, finalExperience.fare.carSurcharge, 0.0)
    }
}