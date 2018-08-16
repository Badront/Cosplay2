package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.TopicEntity
import com.histler.cosplay2.domain.model.Topic
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class TopicMapper @Inject constructor() : EntityMapper<TopicEntity, Topic> {
    override fun mapFromEntity(entity: TopicEntity): Topic {
        return Topic(
                entity.id,
                entity.name,
                entity.cardCode,
                entity.urlCode
        )
    }

    override fun mapToEntity(domain: Topic): TopicEntity {
        return TopicEntity(
                domain.id,
                domain.name,
                domain.cardCode,
                domain.urlCode
        )
    }
}