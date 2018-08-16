package com.histler.cosplay2.presentation

import com.arellomobile.mvp.InjectViewState
import com.histler.base.presentation.BasePresenter
import com.histler.cosplay2.domain.interactor.participant.GetParticipants
import com.histler.cosplay2.presentation.mapper.ParticipantViewMapper
import com.histler.cosplay2.presentation.mapper.TopicViewMapper
import com.histler.cosplay2.presentation.view.BrowseParticipantsView
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
    //TODO()
}