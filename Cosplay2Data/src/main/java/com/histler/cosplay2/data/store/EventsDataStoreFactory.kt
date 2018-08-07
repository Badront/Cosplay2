package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.repository.EventsDataStore
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
open class EventsDataStoreFactory @Inject constructor(
        private val eventsCacheDataStore: EventsCacheDataStore,
        private val eventsRemoteDataStore: EventsRemoteDataStore
) {
    open fun getDataStore(cached: Boolean, cacheExpired: Boolean): EventsDataStore {
        return if (cached && !cacheExpired) {
            eventsCacheDataStore
        } else {
            eventsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): EventsDataStore {
        return eventsCacheDataStore
    }
}