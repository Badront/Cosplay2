package com.histler.cosplay2.data.store

import com.histler.cosplay2.data.model.ScheduleEntity
import com.histler.cosplay2.data.repository.EventsDataStore
import com.histler.cosplay2.data.repository.EventsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 07.08.2018
 */
class EventsRemoteDataStore @Inject constructor(
        private val eventsRemote: EventsRemote
) : EventsDataStore {
    override fun saveSchedule(schedule: List<ScheduleEntity>): Completable {
        throw UnsupportedOperationException("Saving schedule isn't supported here...")
    }

    override fun getSchedule(): Observable<List<ScheduleEntity>> {
        return eventsRemote.getSchedule()
    }

    override fun clearSchedule(): Completable {
        throw UnsupportedOperationException("Clearing schedule isn't supported here...")
    }

}