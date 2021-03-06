package com.microservice.messageservice.resouce

import com.microservice.messageservice.config.EnvProperties
import com.microservice.messageservice.dto.Message
import com.microservice.messageservice.entity.Commit
import com.microservice.messageservice.service.CommitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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

    @PostMapping("/commit/create")
    fun createCommit(@RequestParam description: String,
                     @RequestParam cardId: Int,
                     @RequestParam announcer: String,
                     @RequestParam receiver: String?): Commit {
        val newCommit = Commit(description = description, announcer = announcer, receiver = receiver, cardId = cardId)

        return commitService.create(newCommit)
    }

    @PostMapping("/commit/update")
    fun updateCommit(@RequestParam description: String?,
                     @RequestParam isRead: Boolean?,
                     @RequestParam commitId: Int): Commit {

        return commitService.update(isRead, description, commitId)
    }

    @PostMapping("/commit/remove")
    fun removeCommit(@RequestParam commitId: Int): Message {

        return commitService.remove(commitId)
    }

    @PostMapping("/commit/getByReceiver")
    fun getByReceiver(@RequestParam receiver: String): MutableList<Commit> {

        return commitService.getAllCommitByReceiver(receiver)
    }

    @PostMapping("/commit/getByReceiverContaining")
    fun getByReceiverContaining(@RequestParam receiver: String): MutableList<Commit> {

        return commitService.getAllCommitByReceiverContaining(receiver)
    }

    @PostMapping("/commit/getByCardId")
    fun getByCardId(@RequestParam cardId: Int): MutableList<Commit> {

        return commitService.getCommentsByCardId(cardId)
    }
}