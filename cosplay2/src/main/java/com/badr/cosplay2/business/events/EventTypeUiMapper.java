package com.badr.cosplay2.business.events;

import android.support.annotation.Nullable;

import com.badr.cosplay2.model.event.network.EventType;
import com.badr.cosplay2.model.event.presentation.EventTypeUiListModel;

import javax.inject.Inject;

import ru.badr.base.business.mapper.Mapper;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventTypeUiMapper extends Mapper<EventType, EventTypeUiListModel> {

    @Inject
    public EventTypeUiMapper() {
    }

    @Nullable
    @Override
    public EventTypeUiListModel map(@Nullable EventType value) {
        if (value == null) {
            return null;
        }
        return new EventTypeUiListModel(
                value.getTitle(),
                value.getId(),
                value.getOrder());
    }
}
