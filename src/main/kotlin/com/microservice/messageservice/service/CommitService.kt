package com.microservice.messageservice.service

import com.microservice.messageservice.dto.Message
import com.microservice.messageservice.entity.Commit
import com.microservice.messageservice.repository.CommitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommitService {
    @Autowired
    private lateinit var commitRepository: CommitRepository

    fun create(newCommit: Commit): Commit {
        val updateTime = System.currentTimeMillis()
        newCommit.updateTime = updateTime.toString()

        return commitRepository.save(newCommit)
    }

    fun update(isRead: Boolean?, description: String?, commitId: Int): Message {
        val oldCommitOption = commitRepository.findById(commitId)
        if (oldCommitOption.isEmpty) {
            return Message(false, "no this commit")
        }
        val oldCommit = oldCommitOption.get()
        if (description != null) {
            oldCommit.description = description
        }
        if (isRead != null) {
            oldCommit.isRead = isRead
        }
        commitRepository.save(oldCommit)
        return Message(true, "commit update success")
    }

    fun remove(commitId: Int): Message {
        commitRepository.deleteById(commitId)
        return Message(true, "commit remove success")
    }

    fun getAllCommitByReceiver(receiver: String): Message {
        val commitList = commitRepository.findAllByReceiver(receiver) ?:
        return Message(false, "this receiver have no commit")

        return Message(true, commitList)
    }


}