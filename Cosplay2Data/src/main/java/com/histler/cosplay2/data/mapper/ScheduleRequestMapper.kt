package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ScheduleRequestEntity
import com.histler.cosplay2.domain.model.ScheduleRequest
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class ScheduleRequestMapper @Inject constructor() : EntityMapper<ScheduleRequestEntity, ScheduleRequest> {
    override fun mapFromEntity(entity: ScheduleRequestEntity): ScheduleRequest {
        return ScheduleRequest(
                entity.id,
                entity.name,
                entity.startTime,
                entity.endTime,
                entity.image,
                entity.parentId
        )
    }

    override fun mapToEntity(domain: ScheduleRequest): ScheduleRequestEntity {
        return ScheduleRequestEntity(
                domain.id,
                domain.name,
                domain.startTime,
                domain.endTime,
                domain.image,
                domain.parentId
        )
    }
}