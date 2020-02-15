package com.microservice.noticservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NoticeApplication

fun main(args: Array<String>) {
    runApplication<NoticeApplication>(*args)
}
