package com.histler.cosplay2.data.model

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
data class ParticipantEntity(
        val id: Long?,
        val name: String,
        val image: String?,
        val nomination: NominationEntity,
        val winType: WinTypeEntity?)