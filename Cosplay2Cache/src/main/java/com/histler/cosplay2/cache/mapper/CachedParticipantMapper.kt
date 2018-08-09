package com.histler.cosplay2.cache.mapper

import com.histler.base.cache.mapper.CachedMapper
import com.histler.cosplay2.cache.model.CachedParticipant
import com.histler.cosplay2.cache.model.CachedParticipantNomination
import com.histler.cosplay2.cache.model.CachedParticipantWithNomination
import com.histler.cosplay2.data.model.ParticipantEntity
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 09.08.2018
 */
class CachedParticipantMapper @Inject constructor(
        private val nominationMapper: CachedNominationMapper
) : CachedMapper<CachedParticipantWithNomination, ParticipantEntity> {
    override fun mapFromCached(cached: CachedParticipantWithNomination): ParticipantEntity {
        return ParticipantEntity(
                cached.participant.id,
                cached.participant.name,
                cached.participant.image,
                nominationMapper.mapFromCached(cached.nomination),
                cached.participantNomination.winType
        )
    }

    override fun mapToCached(entity: ParticipantEntity): CachedParticipantWithNomination {
        return CachedParticipantWithNomination(
                CachedParticipant(
                        entity.id,
                        entity.name,
                        entity.image
                ),
                CachedParticipantNomination(
                        entity.id,
                        entity.nomination.id,
                        entity.winType
                ),
                nominationMapper.mapToCached(entity.nomination)
        )
    }
}