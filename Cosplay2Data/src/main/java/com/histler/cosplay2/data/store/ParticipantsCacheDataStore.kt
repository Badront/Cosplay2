package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.model.ParticipantEntity
import com.histler.cosplay2.data.repository.ParticipantsCache
import com.histler.cosplay2.data.repository.ParticipantsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class ParticipantsCacheDataStore @Inject constructor(
        private val participantsCache: ParticipantsCache
) : ParticipantsDataStore {
    override fun clearParticipants(): Completable {
        return participantsCache.clearParticipants()
    }

    override fun saveParticipants(participants: List<ParticipantEntity>): Completable {
        return participantsCache.saveParticipants(participants)
    }

    override fun getParticipants(): Observable<List<ParticipantEntity>> {
        return participantsCache.getParticipants()
    }
}