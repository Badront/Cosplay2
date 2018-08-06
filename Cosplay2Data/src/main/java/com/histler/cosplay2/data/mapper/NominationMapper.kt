package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.NominationEntity
import com.histler.cosplay2.domain.model.Nomination
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class NominationMapper @Inject constructor() : EntityMapper<NominationEntity, Nomination> {
    override fun mapToEntity(domain: Nomination): NominationEntity {
        return NominationEntity(
                domain.id,
                domain.name
        )
    }

    override fun mapFromEntity(entity: NominationEntity): Nomination {
        return Nomination(
                entity.id,
                entity.name
        )
    }
}