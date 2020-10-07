package com.microservice.messageservice.resouce

import com.microservice.messageservice.service.CommitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class AuthResource {
    @Autowired
    private lateinit var commitService: CommitService

    @PostMapping("/auth/permission")
    fun getPermissionResources(@RequestParam username: String): MutableMap<String, List<String>> {
        val commentIdList = commitService.getCommentsByAnnouncer(username).map { comment -> comment.id.toString() }
        val messageServiceClaimMap = mutableMapOf<String, List<String>>()
        messageServiceClaimMap["comments"] = commentIdList

        return messageServiceClaimMap
    }
}