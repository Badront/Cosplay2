package com.histler.cosplay2.domain.repository

import com.histler.cosplay2.domain.model.Participant
import io.reactivex.Observable

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
interface ParticipantsRepository {
    fun getParticipants(sectionName: String? = null): Observable<List<Participant>>
}