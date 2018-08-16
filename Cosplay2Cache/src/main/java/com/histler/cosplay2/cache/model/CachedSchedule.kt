package com.histler.cosplay2.cache.model

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.NodeConstants
import com.histler.cosplay2.cache.db.ScheduleConstants
import com.histler.cosplay2.data.model.ScheduleTypeEntity
import java.util.*

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
@Entity(
        tableName = ScheduleConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedNode::class,
                    parentColumns = arrayOf(NodeConstants.COLUMN_ID),
                    childColumns = arrayOf(ScheduleConstants.COLUMN_NODE_ID),
                    onDelete = ForeignKey.CASCADE
            )),
            (ForeignKey(
                    entity = CachedSchedule::class,
                    parentColumns = arrayOf(ScheduleConstants.COLUMN_NODE_ID),
                    childColumns = arrayOf(ScheduleConstants.COLUMN_PARENT_ID),
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        indices = [
            (Index(value = [ScheduleConstants.COLUMN_NODE_ID])),
            (Index(value = [ScheduleConstants.COLUMN_TIME_END])),
            (Index(value = [ScheduleConstants.COLUMN_TIME_START])),
            (Index(value = [ScheduleConstants.COLUMN_TYPE]))
        ]
)
data class CachedSchedule(
        @PrimaryKey
        @ColumnInfo(name = ScheduleConstants.COLUMN_NODE_ID)
        var nodeId: String,
        @ColumnInfo(name = ScheduleConstants.COLUMN_TIME_START)
        var timeStart: Date,
        @ColumnInfo(name = ScheduleConstants.COLUMN_TIME_END)
        var timeEnd: Date?,
        @ColumnInfo(name = ScheduleConstants.COLUMN_TYPE)
        @TypeConverters(ScheduleTypeConverter::class)
        var type: ScheduleTypeEntity,
        @ColumnInfo(name = ScheduleConstants.COLUMN_PARENT_ID)
        var parentId: String?
)