package com.histler.cosplay2.domain.model

import java.util.*

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
abstract class ScheduleNode(
        val id: String,
        val name: String,
        val startTime: Date,
        val endTime: Date?
)