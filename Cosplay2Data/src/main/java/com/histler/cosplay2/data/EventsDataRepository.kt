package com.histler.cosplay2.data

import com.histler.cosplay2.data.mapper.ScheduleMapper
import com.histler.cosplay2.data.repository.EventsCache
import com.histler.cosplay2.domain.model.ScheduleCard
import com.histler.cosplay2.domain.repository.EventsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class EventsDataRepository @Inject constructor(
        private val scheduleMapper: ScheduleMapper,
        private val cache: EventsCache
//todo data store factory
) : EventsRepository {
    override fun getSchedule(): Observable<List<ScheduleCard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}