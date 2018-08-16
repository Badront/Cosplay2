package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedRequest
import com.histler.cosplay2.cache.model.CachedRequestWithVoting
import com.histler.cosplay2.cache.model.CachedVotingRequest
import com.histler.cosplay2.data.model.RequestEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
class CachedRequestMapper @Inject constructor() : CachedMapper<CachedRequestWithVoting, RequestEntity> {
    override fun mapFromCached(cached: CachedRequestWithVoting): RequestEntity {
        return RequestEntity(
                cached.request.id,
                cached.request.topicId,
                cached.request.name,
                cached.request.image,
                cached.voting.votingNumber.toString(),
                cached.voting.win
        )
    }

    override fun mapToCached(entity: RequestEntity): CachedRequestWithVoting {
        return CachedRequestWithVoting(
                CachedRequest(
                        entity.id,
                        entity.name,
                        entity.topicId,
                        entity.image
                ),
                CachedVotingRequest(
                        entity.id,
                        entity.votingNumber.toInt(),
                        entity.win
                )
        )
    }
}