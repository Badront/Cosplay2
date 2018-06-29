package ru.badr.base.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by abadretdinov
 * on 25.06.2018
 */
public abstract class BaseAdapterDelegate<T extends AdapterComparable<T>> extends AdapterDelegate<List<T>> {

    protected boolean areItemsTheSame(List<T> oldItems, int oldItemPosition, List<T> newItems, int newItemPosition) {
        T oldItem = oldItems.get(oldItemPosition);
        T newItem = newItems.get(newItemPosition);
        return oldItem == newItem || (oldItem != null && oldItem.areItemSame(newItem));
    }

    protected boolean areContentsTheSame(List<T> oldItems, int oldItemPosition, List<T> newItems, int newItemPosition) {
        T oldItem = oldItems.get(oldItemPosition);
        T newItem = newItems.get(newItemPosition);
        return oldItem == newItem || (oldItem != null && oldItem.areContentSame(newItem));
    }


    @NonNull
    @Override
    protected final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    public int getSpanSize(List<T> items, int position, int maxSpanSize) {
        return maxSpanSize;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public static class DelegateViewHolder extends RecyclerView.ViewHolder {

        public DelegateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public DelegateViewHolder(View itemView, @NonNull OnItemClickListener clickListener) {
            this(itemView);
            setClickable(itemView, clickListener);
        }

        public void setClickable(View view, @NonNull OnItemClickListener clickListener) {
            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onClick(position);
                }
            });
        }
    }
}
