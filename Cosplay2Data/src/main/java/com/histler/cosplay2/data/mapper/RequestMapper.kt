package com.histler.cosplay2.data.mapper

import com.histler.base.data.mapper.EntityMapper
import com.histler.cosplay2.data.model.RequestEntity
import com.histler.cosplay2.data.model.WinTypeEntity
import com.histler.cosplay2.domain.model.Request
import com.histler.cosplay2.domain.model.WinType
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 14.08.2018
 */
class RequestMapper @Inject constructor() : EntityMapper<RequestEntity, Request> {
    override fun mapFromEntity(entity: RequestEntity): Request {
        return Request(
                entity.id,
                entity.topicId,
                entity.name,
                entity.image,
                entity.votingNumber,
                when (entity.win) {
                    WinTypeEntity.BRONZE -> WinType.BRONZE
                    WinTypeEntity.SILVER -> WinType.SILVER
                    WinTypeEntity.GOLD -> WinType.GOLD
                    null -> null
                }
        )
    }

    override fun mapToEntity(domain: Request): RequestEntity {
        return RequestEntity(
                domain.id,
                domain.topicId,
                domain.name,
                domain.image,
                domain.votingNumber,
                when (domain.win) {
                    WinType.BRONZE -> WinTypeEntity.BRONZE
                    WinType.SILVER -> WinTypeEntity.SILVER
                    WinType.GOLD -> WinTypeEntity.GOLD
                    null -> null
                }
        )
    }
}