package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ScheduleBreakEntity
import com.histler.cosplay2.domain.model.ScheduleBreak
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleBreakMapper @Inject constructor() : EntityMapper<ScheduleBreakEntity, ScheduleBreak> {
    override fun mapFromEntity(entity: ScheduleBreakEntity): ScheduleBreak {
        return ScheduleBreak(
                entity.id,
                entity.name,
                entity.startTime,
                entity.endTime,
                entity.parentId
        )
    }

    override fun mapToEntity(domain: ScheduleBreak): ScheduleBreakEntity {
        return ScheduleBreakEntity(
                domain.id,
                domain.name,
                domain.startTime,
                domain.endTime,
                domain.parentId
        )
    }
}