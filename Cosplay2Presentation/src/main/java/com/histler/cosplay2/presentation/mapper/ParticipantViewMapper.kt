package com.histler.cosplay2.presentation.mapper

import com.histler.base.presentation.mapper.Mapper
import com.histler.cosplay2.domain.model.Request
import com.histler.cosplay2.domain.model.WinType
import com.histler.cosplay2.presentation.model.ParticipantView
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
class ParticipantViewMapper @Inject constructor() : Mapper<ParticipantView, Request> {
    override fun mapToView(domain: Request): ParticipantView {
        return ParticipantView(
                domain.id,
                domain.topicId,
                domain.name,
                domain.image,
                domain.votingNumber,
                when (domain.win) {
                    WinType.BRONZE -> com.histler.cosplay2.presentation.model.WinType.BRONZE
                    WinType.SILVER -> com.histler.cosplay2.presentation.model.WinType.SILVER
                    WinType.GOLD -> com.histler.cosplay2.presentation.model.WinType.GOLD
                    null -> null
                }
        )
    }
}