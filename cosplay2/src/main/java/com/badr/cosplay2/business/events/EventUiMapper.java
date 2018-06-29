package com.badr.cosplay2.business.events;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.badr.cosplay2.BuildConfig;
import com.badr.cosplay2.model.event.network.Event;
import com.badr.cosplay2.model.event.presentation.EventStatus;
import com.badr.cosplay2.model.event.presentation.EventUiListModel;

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import ru.badr.base.business.mapper.Mapper;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventUiMapper extends Mapper<Event, EventUiListModel> {
    @Inject
    public EventUiMapper() {
    }

    @Nullable
    @Override
    public EventUiListModel map(@Nullable Event value) {
        if (value == null) {
            return null;
        }
        return new EventUiListModel(
                value.getId(),
                value.getEventTypeId(),
                value.getTitle(),
                value.getTime(),
                String.format(Locale.getDefault(), BuildConfig.COSPLAY2_IMAGE_URL, value.getId()),
                getStatus(value.getTimeStatus(), value.getStartTime(), value.getEndTime())
        );
    }

    @Nullable
    public EventStatus getStatus(@Nullable String timeStatus, Date startTime, Date endTime) {
        if (!TextUtils.isEmpty(timeStatus)) {
            if ("past".equals(timeStatus)) {
                return EventStatus.past;
            } else if ("future".equals(timeStatus)) {
                return EventStatus.future;
            } else {
                Date now = new Date();
                if (!startTime.after(now) && !endTime.before(now)) {
                    return EventStatus.now;
                }
            }
        }
        return null;
    }
}
