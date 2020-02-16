package com.microservice.messageservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageApplication

fun main(args: Array<String>) {
    runApplication<MessageApplication>(*args)
}
