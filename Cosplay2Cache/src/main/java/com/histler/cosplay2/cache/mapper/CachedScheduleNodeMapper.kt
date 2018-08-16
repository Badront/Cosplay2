package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.*
import com.histler.cosplay2.data.model.*
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
class CachedScheduleNodeMapper @Inject constructor() : CachedMapper<CachedScheduleWithNode, ScheduleNodeEntity> {
    override fun mapFromCached(cached: CachedScheduleWithNode): ScheduleNodeEntity {
        return when (cached.schedule.type) {
            ScheduleTypeEntity.DAY -> ScheduleDayEntity(
                    cached.node.id,
                    cached.node.title,
                    cached.schedule.timeStart,
                    cached.schedule.timeEnd
            )
            ScheduleTypeEntity.PLACE -> SchedulePlaceEntity(
                    cached.node.id,
                    cached.node.title,
                    cached.schedule.timeStart,
                    cached.schedule.timeEnd,
                    cached.schedule.parentId!!
            )
            ScheduleTypeEntity.BREAK -> ScheduleBreakEntity(
                    cached.node.id,
                    cached.node.title,
                    cached.schedule.timeStart,
                    cached.schedule.timeEnd,
                    cached.schedule.parentId!!
            )
            ScheduleTypeEntity.TOPIC -> ScheduleTopicEntity(
                    cached.node.id,
                    cached.node.title,
                    cached.schedule.timeStart,
                    cached.schedule.timeEnd,
                    cached.schedule.parentId!!,
                    cached.topic!!.id
            )
            ScheduleTypeEntity.EVENT -> ScheduleEventEntity(
                    cached.node.id,
                    cached.node.title,
                    cached.schedule.timeStart,
                    cached.schedule.timeEnd,
                    cached.schedule.parentId!!
            )
            ScheduleTypeEntity.PARTICIPANT -> ScheduleRequestEntity(
                    cached.node.id,
                    cached.node.title,
                    cached.schedule.timeStart,
                    cached.schedule.timeEnd,
                    cached.request!!.image,
                    cached.schedule.parentId!!,
                    cached.request.id
            )
        }
    }

    override fun mapToCached(entity: ScheduleNodeEntity): CachedScheduleWithNode {
        val node = CachedNode(
                entity.id,
                entity.name
        )
        return when (entity) {
            is ScheduleBreakEntity -> CachedScheduleWithNode(
                    CachedSchedule(
                            entity.id,
                            entity.startTime,
                            entity.endTime,
                            ScheduleTypeEntity.BREAK,
                            entity.parentId
                    ), node
            )
            is ScheduleDayEntity -> CachedScheduleWithNode(
                    CachedSchedule(
                            entity.id,
                            entity.startTime,
                            entity.endTime,
                            ScheduleTypeEntity.DAY,
                            null
                    ), node
            )
            is ScheduleEventEntity -> CachedScheduleWithNode(
                    CachedSchedule(
                            entity.id,
                            entity.startTime,
                            entity.endTime,
                            ScheduleTypeEntity.EVENT,
                            entity.parentId
                    ), node
            )
            is SchedulePlaceEntity -> CachedScheduleWithNode(
                    CachedSchedule(
                            entity.id,
                            entity.startTime,
                            entity.endTime,
                            ScheduleTypeEntity.PLACE,
                            entity.parentId
                    ), node
            )
            is ScheduleRequestEntity -> CachedScheduleWithNode(
                    CachedSchedule(
                            entity.id,
                            entity.startTime,
                            entity.endTime,
                            ScheduleTypeEntity.PARTICIPANT,
                            entity.parentId
                    ),
                    node,
                    requestNode = CachedRequestNode(
                            entity.id,
                            entity.requestId
                    )
            )
            is ScheduleTopicEntity -> CachedScheduleWithNode(
                    CachedSchedule(
                            entity.id,
                            entity.startTime,
                            entity.endTime,
                            ScheduleTypeEntity.TOPIC,
                            entity.parentId
                    ),
                    node,
                    topicNode = CachedTopicNode(
                            entity.id,
                            entity.topicId
                    )
            )
            else -> {
                CachedScheduleWithNode(
                        CachedSchedule(
                                entity.id,
                                entity.startTime,
                                entity.endTime,
                                ScheduleTypeEntity.DAY,
                                null
                        ), node
                )
            }
        }
    }
}