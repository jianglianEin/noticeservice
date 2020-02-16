package com.microservice.messageservice.resouce

import com.microservice.messageservice.config.EnvProperties
import com.microservice.messageservice.dto.Message
import com.microservice.messageservice.entity.Commit
import com.microservice.messageservice.service.CommitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class MessageResource {
    @Autowired
    private lateinit var env: EnvProperties

    @Autowired
    private lateinit var commitService: CommitService

    @GetMapping()
    fun hello(): String {
        System.out.println("run in MessageService")
        System.out.println(env.env)
        return "hello world\n" + env.env
    }

    @GetMapping("/commit/create")
    fun createCommit(@RequestParam description: String,
                     @RequestParam announcer: String,
                     @RequestParam receiver: String,
                     @RequestParam cardId: Int): Commit {
        val newCommit = Commit(description = description, announcer = announcer, receiver = receiver, cardId = cardId)

        return commitService.create(newCommit)
    }

    @GetMapping("/commit/update")
    fun updateCommit(@RequestParam description: String?,
                     @RequestParam isRead: Boolean?,
                     @RequestParam commitId: Int): Message {

        return commitService.update(isRead, description, commitId)
    }

    @GetMapping("/commit/remove")
    fun removeCommit(@RequestParam commitId: Int): Message {

        return commitService.remove(commitId)
    }

    @GetMapping("/commit/callReceiver")
    fun callReceiver(@RequestParam receiver: String): Message {

        return commitService.getAllCommitByReceiver(receiver)
    }


}