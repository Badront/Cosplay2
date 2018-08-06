package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.ScheduleEntity
import com.histler.cosplay2.data.model.ScheduleTypeEntity
import com.histler.cosplay2.domain.model.ScheduleCard
import com.histler.cosplay2.domain.model.ScheduleCardType
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class ScheduleMapper @Inject constructor() : EntityMapper<ScheduleEntity, ScheduleCard> {
    override fun mapFromEntity(entity: ScheduleEntity): ScheduleCard {
        return ScheduleCard(
                entity.id,
                entity.name,
                entity.image,
                when (entity.type) {
                    ScheduleTypeEntity.DAY -> ScheduleCardType.DAY
                    ScheduleTypeEntity.BLOCK -> ScheduleCardType.BLOCK
                    ScheduleTypeEntity.PARTICIPANT -> ScheduleCardType.PARTICIPANT
                },
                entity.time
        )
    }

    override fun mapToEntity(domain: ScheduleCard): ScheduleEntity {
        return ScheduleEntity(
                domain.id,
                domain.name,
                domain.image,
                when (domain.type) {
                    ScheduleCardType.DAY -> ScheduleTypeEntity.DAY
                    ScheduleCardType.BLOCK -> ScheduleTypeEntity.BLOCK
                    ScheduleCardType.PARTICIPANT -> ScheduleTypeEntity.PARTICIPANT
                },
                domain.time
        )
    }
}