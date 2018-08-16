package com.histler.cosplay2.remote.mapper

import com.histler.base.remote.mapper.ModelMapper
import com.histler.cosplay2.data.model.TopicEntity
import com.histler.cosplay2.remote.model.Topic
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 15.08.2018
 */
class TopicModelMapper @Inject constructor() : ModelMapper<Topic, TopicEntity> {
    override fun mapFromModel(model: Topic): TopicEntity {
        return TopicEntity(
                model.id,
                model.title,
                model.cardCode,
                model.urlCode
        )
    }
}