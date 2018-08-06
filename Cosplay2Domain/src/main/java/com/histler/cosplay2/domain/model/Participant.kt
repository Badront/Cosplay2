package com.histler.cosplay2.domain.model

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
data class Participant(
        val id: Long,
        val name: String,
        val image: String,
        val nomination: Nomination,
        val winType: WinType?)