package com.tsse

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SportAppApplication

fun main(args: Array<String>) {
    SpringApplication.run(SportAppApplication::class.java, *args)
}
