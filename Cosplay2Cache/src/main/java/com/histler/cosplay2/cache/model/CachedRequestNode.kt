package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import com.histler.cosplay2.cache.db.NodeConstants
import com.histler.cosplay2.cache.db.RequestConstants
import com.histler.cosplay2.cache.db.RequestNodeConstants

/**
 * Created by abadretdinov
 * on 15.08.2018
 */
@Entity(
        tableName = RequestNodeConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedRequest::class,
                    parentColumns = arrayOf(RequestConstants.COLUMN_ID),
                    childColumns = arrayOf(RequestNodeConstants.COLUMN_REQUEST_ID),
                    onDelete = ForeignKey.CASCADE
            )),
            (ForeignKey(
                    entity = CachedNode::class,
                    parentColumns = arrayOf(NodeConstants.COLUMN_ID),
                    childColumns = arrayOf(RequestNodeConstants.COLUMN_NODE_UID),
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        indices = [
            (Index(value = [RequestNodeConstants.COLUMN_NODE_UID])),
            (Index(value = [RequestNodeConstants.COLUMN_REQUEST_ID])),
            (Index(value = [RequestNodeConstants.COLUMN_NODE_UID, RequestNodeConstants.COLUMN_REQUEST_ID]))
        ],
        primaryKeys = [
            (RequestNodeConstants.COLUMN_NODE_UID),
            (RequestNodeConstants.COLUMN_REQUEST_ID)
        ]
)
data class CachedRequestNode(
        @ColumnInfo(name = RequestNodeConstants.COLUMN_NODE_UID)
        val nodeUid: String,
        @ColumnInfo(name = RequestNodeConstants.COLUMN_REQUEST_ID)
        val requestId: Long
)