package com.histler.cosplay2.cache.model

import android.arch.persistence.room.Embedded

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
data class CachedRequestWithNode(
        @Embedded
        val request: CachedRequest,
        @Embedded
        val requestNode: CachedRequestNode,
        @Embedded
        val node: CachedNode
)