package com.histler.cosplay2.domain.interactor.schedule

import com.histler.base.domain.executor.PostExecutionThread
import com.histler.base.domain.interactor.ObservableUseCase
import com.histler.cosplay2.domain.model.ScheduleCard
import com.histler.cosplay2.domain.repository.EventsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 31.07.2018
 */
class GetSchedule @Inject constructor(
        private val eventsRepository: EventsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<ScheduleCard>, Nothing?>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<ScheduleCard>> {
        return eventsRepository.getSchedule()
    }
}