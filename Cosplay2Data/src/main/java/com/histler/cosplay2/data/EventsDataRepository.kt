package com.histler.cosplay2.data

import com.histler.cosplay2.data.mapper.*
import com.histler.cosplay2.data.model.*
import com.histler.cosplay2.data.repository.EventsCache
import com.histler.cosplay2.data.store.EventsDataStoreFactory
import com.histler.cosplay2.domain.model.ScheduleNode
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
        private val breakMapper: ScheduleBreakMapper,
        private val dayMapper: ScheduleDayMapper,
        private val eventMapper: ScheduleEventMapper,
        private val placeMapper: SchedulePlaceMapper,
        private val requestMapper: ScheduleRequestMapper,
        private val topicMapper: ScheduleTopicMapper,
        private val cache: EventsCache,
        private val dataStoreFactory: EventsDataStoreFactory
) : EventsRepository {
    override fun getSchedule(): Observable<List<ScheduleNode>> {
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
                .map { scheduleList ->
                    scheduleList.map {
                        when (it) {
                            is ScheduleBreakEntity -> breakMapper.mapFromEntity(it)
                            is ScheduleDayEntity -> dayMapper.mapFromEntity(it)
                            is ScheduleEventEntity -> eventMapper.mapFromEntity(it)
                            is SchedulePlaceEntity -> placeMapper.mapFromEntity(it)
                            is ScheduleRequestEntity -> requestMapper.mapFromEntity(it)
                            is ScheduleTopicEntity -> topicMapper.mapFromEntity(it)
                            else -> {
                                null as ScheduleNode//todo ???
                            }
                        }
                    }
                }
    }
}