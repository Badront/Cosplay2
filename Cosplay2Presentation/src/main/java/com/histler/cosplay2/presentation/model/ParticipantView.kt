package com.histler.cosplay2.presentation.model

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
data class ParticipantView(
        val id: Long,
        val topicId: Long,
        val name: String,
        val image: String?,
        val votingNumber: String,
        val winType: WinType?
)