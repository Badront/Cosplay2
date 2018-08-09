package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedNomination
import com.histler.cosplay2.data.model.NominationEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
class CachedNominationMapper @Inject constructor() : CachedMapper<CachedNomination, NominationEntity> {
    override fun mapFromCached(cached: CachedNomination): NominationEntity {
        return NominationEntity(
                cached.id,
                cached.name
        )
    }

    override fun mapToCached(entity: NominationEntity): CachedNomination {
        return CachedNomination(
                entity.id,
                entity.name
        )
    }
}