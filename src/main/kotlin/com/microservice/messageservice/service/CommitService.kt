package com.microservice.messageservice.service

import com.microservice.messageservice.dto.Message
import com.microservice.messageservice.entity.Commit
import com.microservice.messageservice.repository.CommitRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommitService {
    @Autowired
    private lateinit var commitRepository: CommitRepository

    private var logger = KotlinLogging.logger {}

    fun create(newCommit: Commit): Commit {
        val updateTime = System.currentTimeMillis()
        newCommit.updateTime = updateTime.toString()

        return commitRepository.save(newCommit)
    }

    fun update(isRead: Boolean?, description: String?, commitId: Int): Commit {
        val oldCommitOption = commitRepository.findById(commitId)
        if (oldCommitOption.isPresent) {
            val oldCommit = oldCommitOption.get()
            if (description != null) oldCommit.description = description
            if (isRead != null) oldCommit.read = isRead

            oldCommit.updateTime = System.currentTimeMillis().toString()

            commitRepository.save(oldCommit)
            logger.info { "commit update success" }

            return oldCommit
        }
        logger.warn { "no this commit"  }

        return Commit()
    }

    fun remove(commitId: Int): Message {
        commitRepository.deleteById(commitId)
        return Message(true, "commit remove success")
    }

    fun getAllCommitByReceiver(receiver: String): MutableList<Commit> {
        return commitRepository.findAllByReceiver(receiver)
    }

    fun getAllCommitByReceiverContaining(receiver: String): MutableList<Commit> {
        val receiverCommit = commitRepository.findAllByReceiverContaining(receiver)
        receiverCommit.removeIf {
            !it.receiver!!.split(",").contains(receiver)
        }
        return receiverCommit
    }

    fun getCommentsByCardId(cardId: Int): MutableList<Commit> {
        return commitRepository.findAllByCardId(cardId)
    }

    fun getCommentsByAnnouncer(announcer: String): MutableList<Commit> {
        return commitRepository.findAllByAnnouncer(announcer)
    }
}