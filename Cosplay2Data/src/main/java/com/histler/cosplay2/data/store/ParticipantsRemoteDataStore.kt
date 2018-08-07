package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.model.ParticipantEntity
import com.histler.cosplay2.data.repository.ParticipantsDataStore
import com.histler.cosplay2.data.repository.ParticipantsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class ParticipantsRemoteDataStore @Inject constructor(
        private val participantsRemote: ParticipantsRemote
) : ParticipantsDataStore {
    override fun clearParticipants(): Completable {
        throw UnsupportedOperationException("Clearing participants isn't supported here...")
    }

    override fun saveParticipants(participants: List<ParticipantEntity>): Completable {
        throw UnsupportedOperationException("Saving participants isn't supported here...")
    }

    override fun getParticipants(): Observable<List<ParticipantEntity>> {
        return participantsRemote.getParticipants()
    }
}