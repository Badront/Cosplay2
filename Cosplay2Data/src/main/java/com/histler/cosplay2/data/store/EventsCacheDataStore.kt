package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.model.ScheduleNodeEntity
import com.histler.cosplay2.data.repository.EventsCache
import com.histler.cosplay2.data.repository.EventsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class EventsCacheDataStore @Inject constructor(
        private val eventsCache: EventsCache
) : EventsDataStore {
    override fun clearSchedule(): Completable {
        return eventsCache.clearSchedule()
    }

    override fun saveSchedule(schedule: List<ScheduleNodeEntity>): Completable {
        return eventsCache.saveSchedule(schedule)
    }

    override fun getSchedule(): Observable<List<ScheduleNodeEntity>> {
        return eventsCache.getSchedule()
    }


}