package com.histler.cosplay2.domain.repository

import com.histler.cosplay2.domain.model.ScheduleCard
import io.reactivex.Observable

/**
 * Created by abadretdinov
 * on 31.07.2018
 */
interface EventsRepository {
    fun getSchedule(): Observable<List<ScheduleCard>>
}