package com.microservice.messageservice.entity

import javax.persistence.*


@Entity
@Table(name = "commit", schema = "public")
class Commit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "announcer")
    var announcer: String? = null

    @Column(name = "receiver")
    var receiver: String? = null

    @Column(name = "update_time")
    var updateTime: String? = null

    @Column(name = "card_id")
    var cardId: Int? = null

    @Column(name = "is_read")
    var read: Boolean? = false

    constructor(description: String? = null,
                announcer: String? = null,
                receiver: String? = "",
                updateTime: String? = null,
                cardId: Int? = 1,
                isRead: Boolean? = false) : this() {
        this.description = description
        this.announcer = announcer
        this.receiver = receiver
        this.updateTime = updateTime
        this.cardId = cardId
        this.read = isRead
    }
}
