package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.cosplay2.cache.db.TopicConstants

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
@Entity(
        tableName = TopicConstants.TABLE_NAME,
        indices = [
            (Index(value = [TopicConstants.COLUMN_ID])),
            (Index(value = [TopicConstants.COLUMN_NAME])),
            (Index(value = [TopicConstants.COLUMN_CARD_CODE])),
            (Index(value = [TopicConstants.COLUMN_URL_CODE]))
        ]
)
data class CachedTopic(
        @PrimaryKey
        @ColumnInfo(name = TopicConstants.COLUMN_ID)
        var id: Long,
        @ColumnInfo(name = TopicConstants.COLUMN_NAME)
        var name: String,
        @ColumnInfo(name = TopicConstants.COLUMN_CARD_CODE)
        var cardCode: String,
        @ColumnInfo(name = TopicConstants.COLUMN_URL_CODE)
        var urlCode: String
)