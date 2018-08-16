package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.SchedulePlaceEntity
import com.histler.cosplay2.domain.model.SchedulePlace
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class SchedulePlaceMapper @Inject constructor() : EntityMapper<SchedulePlaceEntity, SchedulePlace> {
    override fun mapFromEntity(entity: SchedulePlaceEntity): SchedulePlace {
        return SchedulePlace(
                entity.id,
                entity.name,
                entity.startTime,
                entity.endTime,
                entity.parentId
        )
    }

    override fun mapToEntity(domain: SchedulePlace): SchedulePlaceEntity {
        return SchedulePlaceEntity(
                domain.id,
                domain.name,
                domain.startTime,
                domain.endTime,
                domain.parentId
        )
    }
}