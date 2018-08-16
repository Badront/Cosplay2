package com.histler.cosplay2.data.model

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class RequestEntity(
        val id: Long,
        val topicId: Long,
        val name: String,
        val image: String?,
        val votingNumber: String,
        val win: WinTypeEntity?
)