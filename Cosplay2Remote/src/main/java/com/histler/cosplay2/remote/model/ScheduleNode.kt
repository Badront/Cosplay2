package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class ScheduleNode(
        val type: ScheduleType,
        var node: List<ScheduleNode>,
        @SerializedName("request_id") val cardId: Long,
        @SerializedName("card_code") val cardCode: String,
        var card: TopicCard,
        val title: String,
        @SerializedName("time_start") val startTime: Date,
        @SerializedName("time_end") val endTime: Date,
        val collapsed: Boolean
)