package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
open class TopicCard(
        val id: Long,
        @SerializedName("topicName") var topicName: String,
        @SerializedName("topic_id") val topicId: Long,
        @SerializedName("voting_number") val votingNumber: String,
        @SerializedName("voting_title") val votingTitle: String,
        @SerializedName("win") val win: String,
        @SerializedName("user_id") val userId: Long
)