package com.histler.cosplay2.data.repository

import com.histler.cosplay2.data.model.ScheduleEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
interface EventsDataStore {

    fun clearSchedule(): Completable

    fun saveSchedule(schedule: List<ScheduleEntity>): Completable

    fun getSchedule(): Observable<List<ScheduleEntity>>
}