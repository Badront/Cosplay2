package com.badr.cosplay2.model.event.presentation;

import android.support.v4.util.ObjectsCompat;

import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventTypeUiListModel extends UiListItem {
    private final String title;
    private final long typeId;
    private final int order;

    public EventTypeUiListModel(String title, long typeId, int order) {
        this.title = title;
        this.typeId = typeId;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public long getTypeId() {
        return typeId;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public boolean areItemSame(Object item) {
        if (!super.areItemSame(item)) return false;
        return ((EventTypeUiListModel) item).getTypeId() == typeId;
    }

    @Override
    public boolean areContentSame(UiListItem item) {
        EventTypeUiListModel that = (EventTypeUiListModel) item;
        if (!ObjectsCompat.equals(that.title, this.title)) return false;
        if (that.order != this.order) return false;
        return true;
    }
}
