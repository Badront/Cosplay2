package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ScheduleEventEntity
import com.histler.cosplay2.domain.model.ScheduleEvent
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleEventMapper @Inject constructor() : EntityMapper<ScheduleEventEntity, ScheduleEvent> {
    override fun mapFromEntity(entity: ScheduleEventEntity): ScheduleEvent {
        return ScheduleEvent(
                entity.id,
                entity.name,
                entity.startTime,
                entity.endTime,
                entity.parentId
        )
    }

    override fun mapToEntity(domain: ScheduleEvent): ScheduleEventEntity {
        return ScheduleEventEntity(
                domain.id,
                domain.name,
                domain.startTime,
                domain.endTime,
                domain.parentId
        )
    }
}