package ru.badr.base.ui.adapter;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by abadretdinov
 * on 19.04.2018
 */
public abstract class BasePagingDelegationAdapter<T extends AdapterComparable<T>> extends BaseDelegationAdapter<T> {
    private boolean allItemsLoaded = false;

    public void addItems(@NonNull final List<T> newItems) {
        if (newItems.size() == 0) {
            allItemsLoaded = true;
            return;
        }
        List<T> items = getItems();
        int size = getItemCount();
        items.addAll(newItems);
        notifyItemRangeInserted(size, newItems.size());
    }

    public boolean isAllItemsLoaded() {
        return allItemsLoaded;
    }
}
