package com.vangoghscoffee.diamondshare.service

import com.vangoghscoffee.diamondshare.entities.Car
import com.vangoghscoffee.diamondshare.entities.DiamondExperience
import com.vangoghscoffee.diamondshare.entities.Driver
import com.vangoghscoffee.diamondshare.entities.Fare
import org.kie.api.runtime.KieContainer
import org.kie.api.runtime.KieSession
import org.springframework.stereotype.Service

@Service
class DiamondExperienceService(
        private val kieContainer: KieContainer
) {
    fun calculateDiamondFare(selectedCar: Car, allCars: List<Car>, driver: Driver): DiamondExperience {
        val diamondExperience = createEmptyExperience(selectedCar, driver)
        val kieSession: KieSession = kieContainer.newKieSession()
        kieSession.apply {
            setGlobal("diamondExperience", diamondExperience)
            insert(allCars)
            fireAllRules()
            dispose()
        }
        return diamondExperience
    }

    private fun createEmptyExperience(selectedCar: Car, driver: Driver): DiamondExperience {
        var fare: Fare = Fare(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        return DiamondExperience(selectedCar, driver, fare)
    }
}