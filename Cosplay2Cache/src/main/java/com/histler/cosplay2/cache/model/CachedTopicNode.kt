package com.histler.cosplay2.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import com.histler.cosplay2.cache.db.NodeConstants
import com.histler.cosplay2.cache.db.TopicConstants
import com.histler.cosplay2.cache.db.TopicNodeConstants

/**
 * Created by abadretdinov
 * on 15.08.2018
 */
@Entity(
        tableName = TopicNodeConstants.TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = CachedTopic::class,
                    parentColumns = arrayOf(TopicConstants.COLUMN_ID),
                    childColumns = arrayOf(TopicNodeConstants.COLUMN_TOPIC_ID),
                    onDelete = ForeignKey.CASCADE
            )),
            (ForeignKey(
                    entity = CachedNode::class,
                    parentColumns = arrayOf(NodeConstants.COLUMN_ID),
                    childColumns = arrayOf(TopicNodeConstants.COLUMN_NODE_UID),
                    onDelete = ForeignKey.CASCADE
            ))
        ],
        primaryKeys = [
            (TopicNodeConstants.COLUMN_NODE_UID),
            (TopicNodeConstants.COLUMN_TOPIC_ID)
        ],
        indices = [
            (Index(value = [TopicNodeConstants.COLUMN_NODE_UID])),
            (Index(value = [TopicNodeConstants.COLUMN_TOPIC_ID])),
            (Index(value = [TopicNodeConstants.COLUMN_NODE_UID, TopicNodeConstants.COLUMN_TOPIC_ID]))
        ]
)
data class CachedTopicNode(
        @ColumnInfo(name = TopicNodeConstants.COLUMN_NODE_UID)
        val nodeUid: String,
        @ColumnInfo(name = TopicNodeConstants.COLUMN_TOPIC_ID)
        val topicId: Long
)