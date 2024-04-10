package com.architectureteam.multitenat.ms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class MsUsersApplication

fun main(args: Array<String>) {
    runApplication<MsUsersApplication>(*args)
}
