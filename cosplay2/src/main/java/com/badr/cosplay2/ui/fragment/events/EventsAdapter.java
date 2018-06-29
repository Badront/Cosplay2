package com.badr.cosplay2.ui.fragment.events;

import android.support.annotation.NonNull;

import com.badr.cosplay2.ui.adapter_delegate.events.EventAdapterDelegate;
import com.badr.cosplay2.ui.adapter_delegate.events.EventTypeAdapterDelegate;

import ru.badr.base.ui.adapter.BaseDelegationAdapter;
import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventsAdapter extends BaseDelegationAdapter<UiListItem> {
    public EventsAdapter(@NonNull OnEventClickListener onEventClickListener) {
        delegatesManager
                .addDelegate(
                        new EventAdapterDelegate(position -> onEventClickListener.onEventClick(getItem(position))))
                .addDelegate(new EventTypeAdapterDelegate());
    }

}
