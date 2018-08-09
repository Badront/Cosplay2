package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.cosplay2.cache.db.ScheduleBlockConstants
import java.util.*

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(tableName = ScheduleBlockConstants.TABLE_NAME,
        indices = [
            (Index(value = [ScheduleBlockConstants.COLUMN_ID])),
            (Index(value = [ScheduleBlockConstants.COLUMN_NAME])),
            (Index(value = [ScheduleBlockConstants.COLUMN_TIME]))
        ])
data class CachedScheduleBlock(
        @PrimaryKey
        @ColumnInfo(name = ScheduleBlockConstants.COLUMN_ID)
        var id: Long,
        @ColumnInfo(name = ScheduleBlockConstants.COLUMN_NAME)
        var name: String,
        @ColumnInfo(name = ScheduleBlockConstants.COLUMN_TIME)
        var time: Date
)