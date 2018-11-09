package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.repository.TopicsAndRequestsDataStore
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
open class ParticipantsDataStoreFactory @Inject constructor(
        private val participantsCacheDataStore: TopicsAndRequestsCacheDataStore,
        private val participantsRemoteDataStore: TopicsAndRequestsRemoteDataStore
) {

    open fun getDataStore(cached: Boolean, cacheExpired: Boolean): TopicsAndRequestsDataStore {
        return if (cached && !cacheExpired) {
            participantsCacheDataStore
        } else {
            participantsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): TopicsAndRequestsDataStore {
        return participantsCacheDataStore
    }

    open fun getRemoteDataStore(): TopicsAndRequestsDataStore {
        return participantsRemoteDataStore
    }
}