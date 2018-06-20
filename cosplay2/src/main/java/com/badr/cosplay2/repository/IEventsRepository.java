package com.badr.cosplay2.repository;

import com.badr.cosplay2.model.event.Event;
import com.badr.cosplay2.model.event.EventType;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
public interface IEventsRepository {

    Single<List<EventType>> getEventTypes();

    Single<List<Event>> getEvents();

}
