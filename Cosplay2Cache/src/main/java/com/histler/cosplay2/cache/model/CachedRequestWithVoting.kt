package com.histler.cosplay2.cache.model

import android.arch.persistence.room.Embedded

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
data class CachedRequestWithVoting(
        @Embedded
        val request: CachedRequest,
        @Embedded
        val voting: CachedVotingRequest
)