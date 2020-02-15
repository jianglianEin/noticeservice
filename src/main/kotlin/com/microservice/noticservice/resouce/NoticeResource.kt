package com.microservice.noticservice.resouce

import com.microservice.noticservice.config.EnvProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class NoticeResource {
    @Autowired
    lateinit var env: EnvProperties

    @GetMapping()
    fun hello(): String {
        System.out.println("run in NoticeService")
        System.out.println(env.env)
        return "hello world\n" + env.env
    }
}