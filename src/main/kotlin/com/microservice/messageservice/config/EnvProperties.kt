package com.microservice.messageservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource(value = ["classpath:application-dev.yml", "application-local.yml"], ignoreResourceNotFound = true)
class EnvProperties {
    @Value("\${env}")
    lateinit var env: String
}