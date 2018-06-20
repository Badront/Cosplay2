package com.badr.cosplay2.repository;

import com.badr.cosplay2.data.network.ICosplay2Api;
import com.badr.cosplay2.model.event.Event;
import com.badr.cosplay2.model.event.EventType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
public class EventsRepository implements IEventsRepository {
    private final ICosplay2Api api;

    @Inject
    public EventsRepository(ICosplay2Api api) {
        this.api = api;
    }

    @Override
    public Single<List<EventType>> getEventTypes() {
        return api
                .getEventTypes();
    }

    @Override
    public Single<List<Event>> getEvents() {
        return api
                .getEvents()
                .map(events -> events != null && events.getEvents() != null ? events.getEvents() : new ArrayList<Event>());
    }
}
