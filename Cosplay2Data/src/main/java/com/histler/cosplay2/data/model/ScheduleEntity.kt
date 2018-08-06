package com.histler.cosplay2.data.model

import java.util.*

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
data class ScheduleEntity(
        val id: Long,
        val name: String,
        val image: String,
        val type: ScheduleTypeEntity,
        val time: Date)