package com.badr.cosplay2.ui.adapter_delegate.events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.badr.cosplay2.R;
import com.badr.cosplay2.R2;
import com.badr.cosplay2.model.event.presentation.EventTypeUiListModel;

import java.util.List;

import butterknife.BindView;
import ru.badr.base.ui.adapter.BaseAdapterDelegate;
import ru.badr.base.ui.adapter.UiListItem;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public class EventTypeAdapterDelegate extends BaseAdapterDelegate<UiListItem> {
    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(
                inflater.inflate(R.layout.item_event_type, parent, false)
        );
    }

    @Override
    protected boolean isForViewType(@NonNull List<UiListItem> items, int position) {
        return items.get(position).getClass() == EventTypeUiListModel.class;
    }

    @Override
    public int getSpanSize(List<UiListItem> items, int position, int maxSpanSize) {
        return maxSpanSize;
    }

    @Override
    protected void onBindViewHolder(@NonNull List<UiListItem> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        EventTypeUiListModel model = (EventTypeUiListModel) items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.title.setText(model.getTitle());
    }

    static class ViewHolder extends BaseAdapterDelegate.DelegateViewHolder {
        @BindView(R2.id.event_type_title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
