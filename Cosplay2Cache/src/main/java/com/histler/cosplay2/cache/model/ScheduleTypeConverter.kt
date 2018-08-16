package com.histler.cosplay2.cache.model

import android.arch.persistence.room.TypeConverter
import com.histler.cosplay2.data.model.ScheduleTypeEntity

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
open class ScheduleTypeConverter {
    @TypeConverter
    open fun toEntity(nodeType: Int): ScheduleTypeEntity {
        return ScheduleTypeEntity.values()[nodeType]
    }

    @TypeConverter
    open fun fromEntity(nodeType: ScheduleTypeEntity): Int {
        return nodeType.ordinal
    }
}