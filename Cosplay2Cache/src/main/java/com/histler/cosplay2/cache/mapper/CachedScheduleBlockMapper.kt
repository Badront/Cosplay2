package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedScheduleBlock
import com.histler.cosplay2.data.model.ScheduleEntity
import com.histler.cosplay2.data.model.ScheduleTypeEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
class CachedScheduleBlockMapper @Inject constructor() : CachedMapper<CachedScheduleBlock, ScheduleEntity> {
    override fun mapFromCached(cached: CachedScheduleBlock): ScheduleEntity {
        return ScheduleEntity(
                cached.id,
                cached.name,
                null,
                ScheduleTypeEntity.BLOCK,
                cached.time
        )
    }

    override fun mapToCached(entity: ScheduleEntity): CachedScheduleBlock {
        return CachedScheduleBlock(
                entity.id,
                entity.name,
                entity.time
        )
    }
}