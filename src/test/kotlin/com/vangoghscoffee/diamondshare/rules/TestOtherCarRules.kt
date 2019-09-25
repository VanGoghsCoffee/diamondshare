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
class TestOtherCarRules {

    @Autowired
    lateinit var diamondExperienceService: DiamondExperienceService

    @Test
    fun `when no other car is closer than 1m then set location surcharge to 50 cents`() {
        val driver = Driver("David", CreditCardType.VISA, Device("UserAgent", OSType.MAC))
        val selectedCar = Car(Coordinate(53.5484, 9.9925), CarType.PORSCHE, 4)
        val allCars = listOf(
                Car(Coordinate(50.9428 , 6.95907), CarType.PORSCHE, 2),
                Car(Coordinate(48.1408, 11.5553), CarType.PORSCHE, 4),
                Car(Coordinate(52.5249, 13.3697), CarType.DEFENDER, 4),
                Car(Coordinate(48.7839, 9.18068), CarType.DEFENDER, 9)
        )

        val finalExperience = diamondExperienceService.calculateDiamondFare(selectedCar, allCars, driver)

        Assert.assertEquals(0.5, finalExperience.fare.locationSurcharge, 0.0)
    }
}