package com.badr.cosplay2.presentation.view.events;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.badr.base.presentation.BaseMvpView;
import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface EventsView extends BaseMvpView {
    void setEvents(List<UiListItem> events);

    void showRetry(boolean show);

    void showItems(boolean show);

    void showEmpty(boolean show);

    void showLoading(boolean show);
}
