package ru.badr.base.ui.adapter;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public abstract class UiListItem implements AdapterComparable<UiListItem> {

    @Override
    public boolean areItemSame(Object item) {
        if (this == item) return true;
        if (item == null || getClass() != item.getClass()) return false;
        return true;
    }
}
