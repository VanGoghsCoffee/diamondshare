package com.vangoghscoffee.diamondshare.configuration

import org.kie.api.KieServices
import org.kie.api.builder.KieBuilder
import org.kie.api.builder.KieFileSystem
import org.kie.api.builder.KieModule
import org.kie.api.runtime.KieContainer
import org.kie.internal.io.ResourceFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.vangoghscoffee.diamondshare.service")
class DiamondShareConfiguration {
    @Bean
    fun kieContainer(): KieContainer {
        val kieServices: KieServices = KieServices.Factory.get()
        val kieFileSystem: KieFileSystem = kieServices.newKieFileSystem()
        kieFileSystem.apply {
            write(ResourceFactory.newClassPathResource("rules/selected_car.drl"))
            write(ResourceFactory.newClassPathResource("rules/other_cars.drl"))
            write(ResourceFactory.newClassPathResource("rules/driver.drl"))
        }
        val kieBuilder: KieBuilder = kieServices.newKieBuilder(kieFileSystem)
        kieBuilder.buildAll()
        val kieModule: KieModule = kieBuilder.kieModule

        return kieServices.newKieContainer(kieModule.releaseId)
    }
}