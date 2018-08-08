package com.histler.cosplay2.remote.service

import com.histler.cosplay2.remote.model.Plan
import com.histler.cosplay2.remote.model.TopicsAndCards
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
interface Cosplay2ScheduleService {
    @GET("api/events/get_plan")
    fun getSchedule(): Observable<Plan>

    @GET("api/cards/get_topics_and_cards")
    fun getTopicsAndCards(): Observable<TopicsAndCards>
}