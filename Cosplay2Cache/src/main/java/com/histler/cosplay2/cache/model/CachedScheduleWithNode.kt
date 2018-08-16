package com.histler.cosplay2.cache.model

import android.arch.persistence.room.Embedded

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
data class CachedScheduleWithNode(
        @Embedded
        val schedule: CachedSchedule,
        @Embedded
        val node: CachedNode,
        @Embedded
        val requestNode: CachedRequestNode? = null,
        @Embedded
        val request: CachedRequest? = null,
        @Embedded
        val topicNode: CachedTopicNode? = null,
        @Embedded
        val topic: CachedTopic? = null
)