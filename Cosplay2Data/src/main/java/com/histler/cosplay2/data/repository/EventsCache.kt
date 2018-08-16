package com.histler.cosplay2.data.repository

import com.histler.cosplay2.data.model.ScheduleNodeEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
interface EventsCache {

    fun clearSchedule(): Completable

    fun saveSchedule(schedule: List<ScheduleNodeEntity>): Completable

    fun getSchedule(): Observable<List<ScheduleNodeEntity>>

    fun areScheduleCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}