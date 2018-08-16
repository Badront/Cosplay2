package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.histler.cosplay2.cache.db.NodeConstants

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
@Entity(
        tableName = NodeConstants.TABLE_NAME,
        indices = [
            (Index(value = [NodeConstants.COLUMN_ID])),
            (Index(value = [NodeConstants.COLUMN_TITLE]))
        ]
)
data class CachedNode(
        @PrimaryKey
        @ColumnInfo(name = NodeConstants.COLUMN_ID)
        var id: String,
        @ColumnInfo(name = NodeConstants.COLUMN_TITLE)
        var title: String
)