package com.histler.cosplay2.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class ScheduleNode(
        val uid: String,
        val type: ScheduleType,
        var nodes: List<ScheduleNode>?,
        @SerializedName("request_id") val requestId: Long?,
        @SerializedName("card_code") val cardCode: String?,
        @SerializedName("topic_id") val topicId: Long?,
        //members
        val title: String,
        @SerializedName("time_start") val startTime: Date?,
        @SerializedName("time_end") val endTime: Date?,
        @SerializedName("date_start") val startDate: Date?,
        var parentId: String?
)