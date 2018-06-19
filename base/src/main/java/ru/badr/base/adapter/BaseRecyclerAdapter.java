package ru.badr.base.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.badr.base.adapter.viewholder.BaseViewHolder;

/**
 * Created by ABadretdinov
 * 12.12.2014
 * 16:39
 */
public abstract class BaseRecyclerAdapter<T, R extends BaseViewHolder> extends RecyclerView.Adapter<R> {
    protected List<T> mData;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecyclerAdapter(List<T> data) {
        mData = data != null ? data : new ArrayList<T>();
    }

    public void setData(List<T> data) {
        mData = data != null ? data : new ArrayList<T>();
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(T item) {
        int position = mData.indexOf(item);
        if (position >= 0) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mOnItemClickListener = mListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public List<T> getItems() {
        return mData;
    }

}
