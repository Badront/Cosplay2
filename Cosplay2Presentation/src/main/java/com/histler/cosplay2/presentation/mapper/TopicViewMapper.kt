package com.histler.cosplay2.presentation.mapper

import com.histler.base.presentation.mapper.Mapper
import com.histler.cosplay2.domain.model.Topic
import com.histler.cosplay2.presentation.model.TopicView
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
class TopicViewMapper @Inject constructor() : Mapper<TopicView, Topic> {
    override fun mapToView(domain: Topic): TopicView {
        return TopicView(
                domain.id,
                domain.name
        )
    }
}