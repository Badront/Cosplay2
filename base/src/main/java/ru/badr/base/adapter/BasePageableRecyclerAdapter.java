package ru.badr.base.adapter;

import java.util.List;

import ru.badr.base.adapter.viewholder.BaseViewHolder;

/**
 * Created by ABadretdinov
 * 08.07.2015
 * 16:39
 */
public abstract class BasePageableRecyclerAdapter<T, R extends BaseViewHolder> extends BaseRecyclerAdapter<T, R> {
    public BasePageableRecyclerAdapter(List<T> data) {
        super(data);
    }

    public void addData(List<T> data) {
        int position = mData.size();
        int addedSize = 0;
        for (T entity : data) {
            if (!mData.contains(entity)) {
                addedSize++;
                mData.add(entity);
            }
        }
        notifyItemRangeInserted(position, addedSize);
    }
}
