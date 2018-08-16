package com.histler.cosplay2.cache.model

import android.arch.persistence.room.*
import com.histler.cosplay2.cache.db.RequestConstants
import com.histler.cosplay2.cache.db.TopicConstants

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
@Entity(
        tableName = RequestConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedTopic::class,
                    parentColumns = arrayOf(TopicConstants.COLUMN_ID),
                    childColumns = arrayOf(RequestConstants.COLUMN_TOPIC_ID),
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        indices = [
            (Index(value = [RequestConstants.COLUMN_ID])),
            (Index(value = [RequestConstants.COLUMN_NAME])),
            (Index(value = [RequestConstants.COLUMN_TOPIC_ID])),
            (Index(value = [RequestConstants.COLUMN_IMAGE]))
        ]
)
data class CachedRequest(
        @PrimaryKey
        @ColumnInfo(name = RequestConstants.COLUMN_ID)
        var id: Long,
        @ColumnInfo(name = RequestConstants.COLUMN_NAME)
        var name: String,
        @ColumnInfo(name = RequestConstants.COLUMN_TOPIC_ID)
        var topicId: Long,
        @ColumnInfo(name = RequestConstants.COLUMN_IMAGE)
        var image: String?
)