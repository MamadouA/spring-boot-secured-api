package com.ascenders.securedapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecuredApiApplication

fun main(args: Array<String>) {
	runApplication<SecuredApiApplication>(*args)
}
