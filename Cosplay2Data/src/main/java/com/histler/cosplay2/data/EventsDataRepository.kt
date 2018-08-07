package com.histler.cosplay2.data

import com.histler.cosplay2.data.mapper.ScheduleMapper
import com.histler.cosplay2.data.repository.EventsCache
import com.histler.cosplay2.data.store.EventsDataStoreFactory
import com.histler.cosplay2.domain.model.ScheduleCard
import com.histler.cosplay2.domain.repository.EventsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class EventsDataRepository @Inject constructor(
        private val scheduleMapper: ScheduleMapper,
        private val cache: EventsCache,
        private val dataStoreFactory: EventsDataStoreFactory
) : EventsRepository {
    override fun getSchedule(): Observable<List<ScheduleCard>> {
        return Single.zip(
                cache.areScheduleCached(),
                cache.isCacheExpired(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMapObservable {
                    dataStoreFactory
                            .getDataStore(it.first, it.second)
                            .getSchedule()
                }
                .distinct()
                .flatMap { scheduleEvents ->
                    dataStoreFactory
                            .getCacheDataStore()
                            .saveSchedule(scheduleEvents)
                            .andThen(Observable.just(scheduleEvents))
                }
                .map {
                    it.map {
                        scheduleMapper.mapFromEntity(it)
                    }
                }
    }
}