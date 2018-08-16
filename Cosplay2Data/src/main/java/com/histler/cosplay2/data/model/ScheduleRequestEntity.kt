package com.histler.cosplay2.data.model

import java.util.*

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleRequestEntity(
        id: String,
        name: String,
        startTime: Date,
        endTime: Date?,
        val image: String?,
        val parentId: String,
        val requestId: Long) : ScheduleNodeEntity(id, name, startTime, endTime)