package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.adapter.viewholder.HeaderViewHolder;
import ru.badr.base.entity.LineItem;
import ru.badr.cosplay2.adapter.viewholder.ScheduleSectionViewHolder;
import ru.badr.cosplay2.adapter.viewholder.ScheduleViewHolder;
import ru.badr.cosplay2.api.schedule.ScheduleNode;
import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by Badr on 16.11.2015.
 */
public class ScheduleAdapter extends BaseRecyclerAdapter<Object, BaseViewHolder> {
    public static final DateFormat TIME_FORMAT = DateFormat.getTimeInstance(DateFormat.SHORT);
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_SECTION = 1;
    private static final int VIEW_TYPE_CONTENT = 2;

    public ScheduleAdapter(ScheduleNode.List data) {
        super(null);
        mData = setLineItems(data);
    }

    @Override
    public synchronized void setData(List data) {
        mData = setLineItems(data);
        notifyDataSetChanged();
    }

    private List<Object> setLineItems(List list) {
        List<Object> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            ScheduleNode.List data = (ScheduleNode.List) list;
            int sectionFirstPosition = 0;
            for (ScheduleNode node : data) {
                boolean isHeader = false;
                if (node.getType() != ScheduleNode.Type.request) {
                    sectionFirstPosition = result.size();
                    isHeader = true;
                }
                result.add(new LineItem(sectionFirstPosition, isHeader, node));
            }
        }
        return result;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            default:
            case VIEW_TYPE_HEADER:
                view = inflater.inflate(R.layout.header_row, parent, false);
                return new HeaderViewHolder(view);
            case VIEW_TYPE_SECTION:
                view = inflater.inflate(R.layout.schedule_section_row, parent, false);
                return new ScheduleSectionViewHolder(view);
            case VIEW_TYPE_CONTENT:
                view = inflater.inflate(R.layout.schedule_row, parent, false);
                return new ScheduleViewHolder(view, mOnItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        LineItem lineItem = (LineItem) getItem(position);
        GridSLM.LayoutParams lp = GridSLM.LayoutParams.from(holder.itemView.getLayoutParams());
        ScheduleNode node = (ScheduleNode) lineItem.data;
        if (lineItem.isHeader) {
            lp.headerDisplay = LayoutManager.LayoutParams.HEADER_STICKY | LayoutManager.LayoutParams.HEADER_INLINE;
            lp.headerEndMarginIsAuto = true;
            lp.headerStartMarginIsAuto = true;
            if (node.getStartTime() != null) {
                ScheduleSectionViewHolder hHolder = (ScheduleSectionViewHolder) holder;
                hHolder.time.setText(TIME_FORMAT.format(node.getStartTime()));
                hHolder.header.setText(node.getTitle());
            } else {
                HeaderViewHolder hHolder = (HeaderViewHolder) holder;
                hHolder.header.setText(node.getTitle());
            }
        } else {
            ScheduleViewHolder sHolder = (ScheduleViewHolder) holder;

            Context context = sHolder.itemView.getContext();
            Glide.with(context).load(Utils.getSmallCardImageUrl(context, node.getCard())).into(sHolder.photo);
            sHolder.title.setText(node.getTitle());
            sHolder.time.setText(TIME_FORMAT.format(node.getStartTime()));
        }
        lp.setSlm(LinearSLM.ID);
        lp.setFirstPosition(lineItem.sectionFirstPosition);
        holder.itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemViewType(int position) {
        LineItem item = (LineItem) getItem(position);
        ScheduleNode node = (ScheduleNode) item.data;
        if (node.getType() == ScheduleNode.Type.request) {
            return VIEW_TYPE_CONTENT;
        } else if (node.getStartTime() != null) {
            return VIEW_TYPE_SECTION;
        }
        return VIEW_TYPE_HEADER;

    }
}
