package com.microservice.messageservice.repository

import com.microservice.messageservice.entity.Commit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommitRepository: JpaRepository<Commit, Int> {
    fun findAllByReceiver(receiver: String): MutableList<Commit>
    fun findAllByCardId(cardId: Int): MutableList<Commit>
    fun findAllByReceiverContaining(receiver: String): MutableList<Commit>
}