package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.repository.ParticipantsDataStore
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
open class ParticipantsDataStoreFactory @Inject constructor(
        private val participantsCacheDataStore: ParticipantsCacheDataStore,
        private val participantsRemoteDataStore: ParticipantsRemoteDataStore
) {

    open fun getDataStore(cached: Boolean, cacheExpired: Boolean): ParticipantsDataStore {
        return if (cached && !cacheExpired) {
            participantsCacheDataStore
        } else {
            participantsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): ParticipantsDataStore {
        return participantsCacheDataStore
    }
}