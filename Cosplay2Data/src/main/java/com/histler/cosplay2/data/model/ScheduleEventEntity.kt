package com.histler.cosplay2.data.model

import java.util.*

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleEventEntity(
        id: String,
        name: String,
        startTime: Date,
        endTime: Date?,
        val parentId: String) : ScheduleNodeEntity(id, name, startTime, endTime)