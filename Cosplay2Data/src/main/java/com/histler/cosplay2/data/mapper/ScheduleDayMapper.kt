package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ScheduleDayEntity
import com.histler.cosplay2.domain.model.ScheduleDay
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleDayMapper @Inject constructor() : EntityMapper<ScheduleDayEntity, ScheduleDay> {
    override fun mapFromEntity(entity: ScheduleDayEntity): ScheduleDay {
        return ScheduleDay(
                entity.id,
                entity.name,
                entity.startTime,
                entity.endTime
        )
    }

    override fun mapToEntity(domain: ScheduleDay): ScheduleDayEntity {
        return ScheduleDayEntity(
                domain.id,
                domain.name,
                domain.startTime,
                domain.endTime
        )
    }
}