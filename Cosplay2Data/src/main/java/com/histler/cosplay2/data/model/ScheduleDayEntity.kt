package com.histler.cosplay2.data.model

import java.util.*

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleDayEntity(
        id: String,
        name: String,
        startTime: Date,
        endTime: Date?) : ScheduleNodeEntity(id, name, startTime, endTime)