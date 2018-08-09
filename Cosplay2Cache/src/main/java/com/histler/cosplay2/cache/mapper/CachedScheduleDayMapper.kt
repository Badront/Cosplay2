package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedScheduleDay
import com.histler.cosplay2.data.model.ScheduleEntity
import com.histler.cosplay2.data.model.ScheduleTypeEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
class CachedScheduleDayMapper @Inject constructor() : CachedMapper<CachedScheduleDay, ScheduleEntity> {
    override fun mapFromCached(cached: CachedScheduleDay): ScheduleEntity {
        return ScheduleEntity(
                cached.id,
                cached.name,
                null,
                ScheduleTypeEntity.DAY,
                cached.time
        )
    }

    override fun mapToCached(entity: ScheduleEntity): CachedScheduleDay {
        return CachedScheduleDay(
                entity.id,
                entity.name,
                entity.time
        )
    }
}