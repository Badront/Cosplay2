package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ParticipantEntity
import com.histler.cosplay2.data.model.WinTypeEntity
import com.histler.cosplay2.domain.model.Participant
import com.histler.cosplay2.domain.model.WinType
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class ParticipantMapper @Inject constructor(
        private val nominationMapper: NominationMapper
) : EntityMapper<ParticipantEntity, Participant> {
    override fun mapFromEntity(entity: ParticipantEntity): Participant {
        return Participant(
                entity.id,
                entity.name,
                entity.image,
                nominationMapper.mapFromEntity(entity.nomination),
                when (entity.winType) {
                    WinTypeEntity.BRONZE -> WinType.BRONZE
                    WinTypeEntity.SILVER -> WinType.SILVER
                    WinTypeEntity.GOLD -> WinType.GOLD
                    null -> null
                }
        )
    }

    override fun mapToEntity(domain: Participant): ParticipantEntity {
        return ParticipantEntity(
                domain.id,
                domain.name,
                domain.image,
                nominationMapper.mapToEntity(domain.nomination),
                when (domain.winType) {
                    WinType.BRONZE -> WinTypeEntity.BRONZE
                    WinType.SILVER -> WinTypeEntity.SILVER
                    WinType.GOLD -> WinTypeEntity.GOLD
                    null -> null
                }
        )
    }
}