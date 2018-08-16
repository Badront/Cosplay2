package com.histler.cosplay2.domain.model

import java.util.*

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleRequest(
        id: String,
        name: String,
        startTime: Date,
        endTime: Date?,
        val image: String?,
        val parentId: String) : ScheduleNode(id, name, startTime, endTime)