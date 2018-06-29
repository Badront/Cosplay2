package com.badr.cosplay2.model.event.presentation;

import android.support.annotation.Nullable;
import android.support.v4.util.ObjectsCompat;

import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventUiListModel extends UiListItem {
    private final long id;
    private final long typeId;
    private final String title;
    private final String dates;
    private final String image;
    @Nullable
    private final EventStatus status;

    public EventUiListModel(
            long id,
            long typeId,
            String title,
            String dates,
            String image,
            @Nullable EventStatus status) {
        this.id = id;
        this.typeId = typeId;
        this.title = title;
        this.dates = dates;
        this.image = image;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDates() {
        return dates;
    }

    @Nullable
    public EventStatus getStatus() {
        return status;
    }

    @Override
    public boolean areItemSame(Object item) {
        if (!super.areItemSame(item)) return false;
        return ((EventUiListModel) item).getId() == id;
    }

    @Override
    public boolean areContentSame(UiListItem item) {
        EventUiListModel that = (EventUiListModel) item;
        if (!ObjectsCompat.equals(that.image, this.image)) return false;
        if (!ObjectsCompat.equals(that.typeId, this.typeId)) return false;
        if (!ObjectsCompat.equals(that.title, this.title)) return false;
        if (!ObjectsCompat.equals(that.dates, this.dates)) return false;
        if (!ObjectsCompat.equals(that.status, this.dates)) return false;
        return true;
    }
}
