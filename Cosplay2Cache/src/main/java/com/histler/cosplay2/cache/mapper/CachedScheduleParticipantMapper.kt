package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedParticipant
import com.histler.cosplay2.cache.model.CachedParticipantWithSchedule
import com.histler.cosplay2.cache.model.CachedScheduleParticipant
import com.histler.cosplay2.data.model.ScheduleEntity
import com.histler.cosplay2.data.model.ScheduleTypeEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
class CachedScheduleParticipantMapper @Inject constructor() : CachedMapper<CachedParticipantWithSchedule, ScheduleEntity> {
    override fun mapFromCached(cached: CachedParticipantWithSchedule): ScheduleEntity {
        return ScheduleEntity(
                cached.participant.id,
                cached.participant.name,
                cached.participant.image,
                ScheduleTypeEntity.PARTICIPANT,
                cached.schedule.time
        )
    }

    override fun mapToCached(entity: ScheduleEntity): CachedParticipantWithSchedule {
        return CachedParticipantWithSchedule(
                CachedParticipant(
                        entity.id,
                        entity.name,
                        entity.image
                ),
                CachedScheduleParticipant(
                        entity.id,
                        entity.time
                )
        )
    }
}