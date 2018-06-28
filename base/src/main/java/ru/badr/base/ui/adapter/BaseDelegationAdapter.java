package ru.badr.base.ui.adapter;

import android.support.v7.util.DiffUtil;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.List;

/**
 * Created by abadretdinov
 * on 30.01.2018
 */

public abstract class BaseDelegationAdapter<T extends AdapterComparable<T>> extends ListDelegationAdapter<List<T>> {


    public <E extends T> E getItem(int position) {
        return (E) items.get(position);
    }

    @Override
    public void setItems(final List<T> items) {
        final List<T> oldItems = getItems();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldItems != null ? oldItems.size() : 0;
            }

            @Override
            public int getNewListSize() {
                return items != null ? items.size() : 0;
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                int wasType = delegatesManager.getItemViewType(oldItems, oldItemPosition);
                int newType = delegatesManager.getItemViewType(items, newItemPosition);
                if (wasType != newType) {
                    return false;
                }
                AdapterDelegate<List<T>> delegate = delegatesManager.getDelegateForViewType(wasType);
                if (delegate instanceof BaseAdapterDelegate) {
                    return ((BaseAdapterDelegate<T>) delegate).areItemsTheSame(oldItems, oldItemPosition, items, newItemPosition);
                }
                return false;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                int wasType = delegatesManager.getItemViewType(oldItems, oldItemPosition);
                int newType = delegatesManager.getItemViewType(items, newItemPosition);
                if (wasType != newType) {
                    return false;
                }
                AdapterDelegate<List<T>> delegate = delegatesManager.getDelegateForViewType(wasType);
                if (delegate instanceof BaseAdapterDelegate) {
                    return ((BaseAdapterDelegate<T>) delegate).areContentsTheSame(oldItems, oldItemPosition, items, newItemPosition);
                }
                return false;
            }
        });
        super.setItems(items);
        diffResult.dispatchUpdatesTo(this);
    }

    public int getSpanSize(int position, int maxSpanSize) {
        int type = delegatesManager.getItemViewType(items, position);
        AdapterDelegate<List<T>> delegate = delegatesManager.getDelegateForViewType(type);
        if (delegate instanceof BaseAdapterDelegate) {
            return ((BaseAdapterDelegate<T>) delegate).getSpanSize(items, position, maxSpanSize);
        }
        return 1;
    }
}
