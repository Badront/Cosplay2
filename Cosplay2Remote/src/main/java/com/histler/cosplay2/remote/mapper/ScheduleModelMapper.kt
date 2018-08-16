package com.histler.cosplay2.remote.mapper

import com.histler.base.remote.mapper.ModelMapper
import com.histler.cosplay2.data.model.*
import com.histler.cosplay2.data.repository.UrlProvider
import com.histler.cosplay2.remote.model.Request
import com.histler.cosplay2.remote.model.ScheduleNode
import com.histler.cosplay2.remote.model.ScheduleType
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class ScheduleModelMapper @Inject constructor(private val urlProvider: UrlProvider) : ModelMapper<Pair<ScheduleNode, Request?>, ScheduleNodeEntity> {
    override fun mapFromModel(model: Pair<ScheduleNode, Request?>): ScheduleNodeEntity {
        val (node, request) = model
        return when (node.type) {
            ScheduleType.DAY -> ScheduleDayEntity(
                    node.uid,
                    node.title,
                    node.startTime ?: node.startDate!!,
                    node.endTime
            )
            ScheduleType.PLACE -> SchedulePlaceEntity(
                    node.uid,
                    node.title,
                    node.startTime!!,
                    node.endTime,
                    node.parentId!!
            )
            ScheduleType.EVENT -> ScheduleEventEntity(
                    node.uid,
                    node.title,
                    node.startTime!!,
                    node.endTime,
                    node.parentId!!
            )
            ScheduleType.TOPIC -> ScheduleTopicEntity(
                    node.uid,
                    node.title,
                    node.startTime!!,
                    node.endTime,
                    node.parentId!!,
                    node.topicId!!
            )
            ScheduleType.REQUEST -> ScheduleRequestEntity(
                    node.uid,
                    node.title,
                    node.startTime!!,
                    node.endTime,
                    request?.let { getImageUrl(it) },
                    node.parentId!!,
                    node.requestId!!
            )
            ScheduleType.BREAK -> ScheduleBreakEntity(
                    node.uid,
                    node.title,
                    node.startTime!!,
                    node.endTime,
                    node.parentId!!
            )
        }
    }

    private fun getImageUrl(card: Request): String {
        return if (card.image != null) {
            urlProvider.getBaseFestivalUrl() + "uploads/" + urlProvider.getBaseFestivalUrl() + "/" + card.id + "/" + card.image.fileName + "_large.jpg"
        } else {
            urlProvider.getBaseFestivalUrl() + "images/avatars/" + card.userId + "_full.png"
        }
    }
}