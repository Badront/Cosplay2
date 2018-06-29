package com.badr.cosplay2.ui.fragment.events;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.badr.cosplay2.R;
import com.badr.cosplay2.presentation.presenter.events.EventsPresenter;
import com.badr.cosplay2.presentation.view.events.EventsView;

import java.util.List;

import javax.inject.Inject;

import ru.badr.base.ui.adapter.UiListItem;
import ru.badr.base.ui.fragment.BaseMvpFragment;
import ru.badr.base.ui.fragment.RecyclerStateFragment;

/**
 * Created by abadretdinov
 * on 25.06.2018
 */
public class EventsFragment extends BaseMvpFragment implements EventsView {
    @InjectPresenter
    @Inject
    EventsPresenter eventsPresenter;
    private RecyclerStateFragment eventsFragment;
    private EventsAdapter adapter;

    @ProvidePresenter
    EventsPresenter providePresenter() {
        return eventsPresenter;
    }

    //https://material.io/collections/developer-tutorials/#
    @Override
    public int getLayoutId() {
        return R.layout.fragment_events_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventsFragment = (RecyclerStateFragment) getChildFragmentManager().findFragmentById(R.id.recycler_fragment);
        setAdapter(new EventsAdapter(eventsPresenter));
        eventsFragment.setOnRetryClick(view -> eventsPresenter.onRetryClick());
    }

    private EventsAdapter getAdapter() {
        return adapter;
    }

    private void setAdapter(EventsAdapter adapter) {
        this.adapter = adapter;
        eventsFragment.setAdapter(adapter);
    }

    @Override
    public void setEvents(List<UiListItem> events) {
        getAdapter().setItems(events);
    }

    @Override
    public void showRetry(boolean show) {
        eventsFragment.showRetry(show);
    }

    @Override
    public void showItems(boolean show) {
        eventsFragment.showList(show);
    }

    @Override
    public void showEmpty(boolean show) {
        eventsFragment.showEmptyState(show);
    }

    @Override
    public void showLoading(boolean show) {
        eventsFragment.showLoadingState(show);
    }
}
