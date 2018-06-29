package com.badr.cosplay2.presentation.presenter.events;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.badr.cosplay2.business.events.EventsInteractor;
import com.badr.cosplay2.model.event.presentation.EventUiListModel;
import com.badr.cosplay2.presentation.view.events.EventsView;
import com.badr.cosplay2.ui.fragment.events.OnEventClickListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ru.badr.base.SchedulersProvider;
import ru.badr.base.di.scope.Presenter;
import ru.badr.base.presentation.BaseMvpPresenter;
import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
@InjectViewState
@Presenter
public class EventsPresenter extends BaseMvpPresenter<EventsView> implements OnEventClickListener {
    private final EventsInteractor eventsInteractor;
    private Disposable eventsDisposable;

    @Inject
    public EventsPresenter(
            SchedulersProvider schedulersProvider,
            EventsInteractor eventsInteractor) {
        super(schedulersProvider);
        this.eventsInteractor = eventsInteractor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showRetry(false);
        loadEvents();
    }

    private void loadEvents() {
        dispose(eventsDisposable);
        eventsDisposable =
                eventsInteractor
                        .getEventsWithTypes()
                        .doOnSubscribe(d -> {
                            getViewState().showItems(false);
                            getViewState().showEmpty(false);
                            getViewState().showLoading(true);
                        })
                        .observeOn(schedulersProvider.mainThread())
                        .subscribe(this::onEventsLoaded, this::onEventsLoadError);
    }

    private void onEventsLoaded(List<UiListItem> uiListItems) {
        getViewState().showLoading(false);
        boolean isEmpty = uiListItems.isEmpty();
        getViewState().showEmpty(isEmpty);
        getViewState().showItems(!isEmpty);
        getViewState().setEvents(uiListItems);
    }

    private void onEventsLoadError(Throwable throwable) {
        getViewState().showLoading(false);
        getViewState().showEmpty(false);
        getViewState().showItems(false);
        getViewState().showRetry(true);
    }

    public void onRetryClick() {
        getViewState().showRetry(false);
        loadEvents();
    }

    @Override
    public void onEventClick(@NonNull EventUiListModel model) {
        //todo
    }
}
