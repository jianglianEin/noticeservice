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
        if (oldCommitOption.isEmpty) {
            logger.warn { "no this commit"  }

            return Commit()
        }
        val oldCommit = oldCommitOption.get()
        if (description != null) {
            oldCommit.description = description
        }
        if (isRead != null) {
            oldCommit.read = isRead
        }
        commitRepository.save(oldCommit)
        logger.info { "commit update success" }

        return oldCommit
    }

    fun remove(commitId: Int): Message {
        commitRepository.deleteById(commitId)
        return Message(true, "commit remove success")
    }

    fun getAllCommitByReceiver(receiver: String): MutableList<Commit> {
        return commitRepository.findAllByReceiver(receiver)
    }
}