package com.histler.cosplay2.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by abadretdinov
 * on 16.08.2018
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface BrowseParticipantsView : MvpView {
    fun showParticipants(show: Boolean)
    fun showReload(show: Boolean)
    fun showError(message: String)
    fun hideError()
}