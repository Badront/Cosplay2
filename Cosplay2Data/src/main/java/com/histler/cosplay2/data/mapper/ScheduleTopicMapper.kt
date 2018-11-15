package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ScheduleTopicEntity
import com.histler.cosplay2.domain.model.ScheduleTopic
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleTopicMapper @Inject constructor() : EntityMapper<ScheduleTopicEntity, ScheduleTopic> {
    override fun mapFromEntity(entity: ScheduleTopicEntity): ScheduleTopic {
        return ScheduleTopic(
                entity.id,
                entity.name,
                entity.startTime,
                entity.endTime,
                entity.parentId
        )
    }

    override fun mapToEntity(domain: ScheduleTopic): ScheduleTopicEntity {
        return ScheduleTopicEntity(
                domain.id,
                domain.name,
                domain.startTime,
                domain.endTime,
                domain.parentId,
                0
        )
    }
}