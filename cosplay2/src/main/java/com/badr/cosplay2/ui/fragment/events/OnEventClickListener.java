package com.badr.cosplay2.ui.fragment.events;

import android.support.annotation.NonNull;

import com.badr.cosplay2.model.event.presentation.EventUiListModel;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public interface OnEventClickListener {
    void onEventClick(@NonNull EventUiListModel model);
}
