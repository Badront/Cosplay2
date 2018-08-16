package com.histler.cosplay2.domain.model

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
data class Request(
        val id: Long,
        val topicId: Long,
        val name: String,
        val image: String?,
        val votingNumber: String,
        val win: WinType?
)