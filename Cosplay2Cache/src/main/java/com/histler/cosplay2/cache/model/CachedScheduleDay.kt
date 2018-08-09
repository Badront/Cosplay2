package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.cosplay2.cache.db.ScheduleDayConstants
import java.util.*

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
@Entity(tableName = ScheduleDayConstants.TABLE_NAME,
        indices = [
            (Index(value = [ScheduleDayConstants.COLUMN_ID])),
            (Index(value = [ScheduleDayConstants.COLUMN_NAME])),
            (Index(value = [ScheduleDayConstants.COLUMN_TIME]))
        ])
data class CachedScheduleDay(
        @PrimaryKey
        @ColumnInfo(name = ScheduleDayConstants.COLUMN_ID)
        var id: Long,
        @ColumnInfo(name = ScheduleDayConstants.COLUMN_NAME)
        var name: String,
        @ColumnInfo(name = ScheduleDayConstants.COLUMN_TIME)
        var time: Date
)