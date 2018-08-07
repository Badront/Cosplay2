package com.histler.cosplay2.data.repository

import com.histler.cosplay2.data.model.ParticipantEntity
import io.reactivex.Observable

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
interface ParticipantsRemote {

    fun getParticipants(): Observable<List<ParticipantEntity>>
}