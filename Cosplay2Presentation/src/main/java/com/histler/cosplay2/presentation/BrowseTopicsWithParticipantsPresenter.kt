package com.histler.cosplay2.presentation

import com.arellomobile.mvp.InjectViewState
import com.histler.base.presentation.BasePresenter
import com.histler.cosplay2.domain.interactor.participant.GetParticipants
import com.histler.cosplay2.domain.model.Request
import com.histler.cosplay2.domain.model.Topic
import com.histler.cosplay2.presentation.mapper.ParticipantViewMapper
import com.histler.cosplay2.presentation.mapper.TopicViewMapper
import com.histler.cosplay2.presentation.view.BrowseParticipantsView
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
@InjectViewState
class BrowseTopicsWithParticipantsPresenter @Inject constructor(
        private val getParticipants: GetParticipants,
        private val topicMapper: TopicViewMapper,
        private val participantMapper: ParticipantViewMapper
) : BasePresenter<BrowseParticipantsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(true)
        getParticipants.execute(ParticipantsSubscriber())
        //getParticipants.execute()
    }


    inner class ParticipantsSubscriber : DisposableObserver<Map<Topic, List<Request>>>() {
        override fun onComplete() {}

        override fun onNext(t: Map<Topic, List<Request>>) {
            viewState.showReload(false)
            viewState.showLoading(false)
            val result = mutableListOf<Any>()
            t.forEach { topic, requests ->
                result.add(topicMapper.mapToView(topic))
                result.addAll(requests.map { participantMapper.mapToView(it) })
            }
            viewState.setParticipants(result)
            viewState.showParticipants(true)
        }

        override fun onError(e: Throwable) {
            viewState.showReload(false)
            viewState.showLoading(false)
            viewState.showParticipants(true)
            viewState.showError(e.localizedMessage)
        }

    }

}