package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedTopic
import com.histler.cosplay2.data.model.TopicEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
class CachedTopicMapper @Inject constructor() : CachedMapper<CachedTopic, TopicEntity> {
    override fun mapFromCached(cached: CachedTopic): TopicEntity {
        return TopicEntity(
                cached.id,
                cached.name,
                cached.cardCode,
                cached.urlCode
        )
    }

    override fun mapToCached(entity: TopicEntity): CachedTopic {
        return CachedTopic(
                entity.id,
                entity.name,
                entity.cardCode,
                entity.urlCode
        )
    }
}