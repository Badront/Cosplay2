package com.badr.cosplay2.business.events;

import com.badr.cosplay2.model.event.network.Event;
import com.badr.cosplay2.model.event.network.EventType;
import com.badr.cosplay2.model.event.presentation.EventTypeUiListModel;
import com.badr.cosplay2.model.event.presentation.EventUiListModel;
import com.badr.cosplay2.repository.EventsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import ru.badr.base.SchedulersProvider;
import ru.badr.base.business.BaseInteractor;
import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventsInteractor extends BaseInteractor {
    private final EventsRepository eventsRepository;
    private final EventUiMapper eventUiMapper;
    private final EventTypeUiMapper eventTypeUiMapper;

    @Inject
    public EventsInteractor(
            SchedulersProvider schedulersProvider,
            EventsRepository eventsRepository,
            EventUiMapper eventUiMapper,
            EventTypeUiMapper eventTypeUiMapper) {
        super(schedulersProvider);
        this.eventsRepository = eventsRepository;
        this.eventUiMapper = eventUiMapper;
        this.eventTypeUiMapper = eventTypeUiMapper;
    }


    public Single<List<UiListItem>> getEventsWithTypes() {
        return Single
                .zip(
                        eventsRepository
                                .getEventTypes()
                                //sort from 1st order to last
                                .map(eventTypes -> {
                                    List<EventType> sorted = new ArrayList<>(eventTypes);
                                    Collections.sort(sorted, (type1, type2) -> Integer.compare(type1.getOrder(), type2.getOrder()));
                                    return sorted;
                                }),
                        eventsRepository
                                .getEvents()
                                //sort from last to earliest
                                .map(events -> {
                                    List<Event> sorted = new ArrayList<>(events);
                                    Collections.sort(sorted, (event1, event2) -> event2.getStartTime().compareTo(event1.getStartTime()));
                                    return sorted;
                                }),
                        (eventTypes, events) -> {
                            List<UiListItem> items = new ArrayList<>(eventTypeUiMapper.map(eventTypes));
                            List<EventUiListModel> eventUiModels = eventUiMapper.map(events);
                            //for each type we're adding its events after him
                            for (int i = items.size() - 1; i >= 0; i--) {
                                EventTypeUiListModel type = (EventTypeUiListModel) items.get(i);
                                Iterator<EventUiListModel> iterator = eventUiModels.iterator();
                                while (iterator.hasNext()) {
                                    EventUiListModel event = iterator.next();
                                    if (event.getTypeId() == type.getTypeId()) {
                                        items.add(i + 1, event);
                                        iterator.remove();
                                    }
                                }
                            }
                            return items;
                        }
                )
                .subscribeOn(schedulersProvider.io());
    }


}
