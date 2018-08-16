package com.histler.cosplay2.domain.interactor.participant

import com.histler.base.domain.executor.PostExecutionThread
import com.histler.base.domain.interactor.ObservableUseCase
import com.histler.cosplay2.domain.model.Request
import com.histler.cosplay2.domain.model.Topic
import com.histler.cosplay2.domain.repository.ParticipantsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 06.08.2018
 */
class GetParticipants @Inject constructor(
        private val participantsRepository: ParticipantsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Map<Topic, List<Request>>, GetParticipants.Params?>(postExecutionThread) {
    override fun buildUseCaseObservable(params: GetParticipants.Params?): Observable<Map<Topic, List<Request>>> {
        return participantsRepository.getParticipants(params?.section)
    }

    data class Params constructor(val section: String?) {
        companion object {
            fun forSection(section: String? = null): Params {
                return Params(section)
            }
        }
    }
}