package com.histler.cosplay2.data

import com.histler.cosplay2.data.mapper.ParticipantMapper
import com.histler.cosplay2.domain.model.Participant
import com.histler.cosplay2.domain.repository.ParticipantsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class ParticipantsDataRepository @Inject constructor(
        private val participantMapper: ParticipantMapper
) : ParticipantsRepository {
    override fun getParticipants(sectionName: String?): Observable<List<Participant>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}