package com.badr.cosplay2.ui.adapter_delegate.events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.badr.cosplay2.R;
import com.badr.cosplay2.R2;
import com.badr.cosplay2.model.event.presentation.EventUiListModel;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import ru.badr.base.ui.adapter.BaseAdapterDelegate;
import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventAdapterDelegate extends BaseAdapterDelegate<UiListItem> {
    private final OnItemClickListener onItemClickListener;

    public EventAdapterDelegate(@NonNull OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected boolean isForViewType(@NonNull List<UiListItem> items, int position) {
        return items.get(position).getClass() == EventUiListModel.class;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(
                inflater.inflate(R.layout.item_event, parent, false),
                onItemClickListener
        );
    }

    @Override
    public int getSpanSize(List<UiListItem> items, int position, int maxSpanSize) {
        return maxSpanSize / 2;
    }

    @Override
    protected void onBindViewHolder(@NonNull List<UiListItem> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        EventUiListModel model = (EventUiListModel) items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.title.setText(model.getTitle());
        viewHolder.dates.setText(model.getDates());
        Glide
                .with(viewHolder.itemView.getContext())
                .load(model.getImage())
                .into(viewHolder.image);

    }

    static class ViewHolder extends BaseAdapterDelegate.DelegateViewHolder {
        @BindView(R2.id.event_image)
        ImageView image;
        @BindView(R2.id.event_title)
        TextView title;
        @BindView(R2.id.event_time)
        TextView dates;

        public ViewHolder(
                View itemView,
                @NonNull OnItemClickListener clickListener) {
            super(itemView, clickListener);
        }
    }
}
