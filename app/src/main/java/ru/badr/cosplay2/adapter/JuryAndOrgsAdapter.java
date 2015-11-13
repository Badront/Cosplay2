package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;

import java.util.ArrayList;
import java.util.List;

import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.adapter.viewholder.HeaderViewHolder;
import ru.badr.base.entity.LineItem;
import ru.badr.cosplay2.adapter.viewholder.JuryAndOrgViewHolder;
import ru.badr.cosplay2.api.JuryEntity;
import ru.badr.cosplay2.api.JurySectionEntity;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 18:45
 */
public class JuryAndOrgsAdapter extends BaseRecyclerAdapter<Object, BaseViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_CONTENT = 1;

    public JuryAndOrgsAdapter(JurySectionEntity.List data) {
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
            JurySectionEntity.List data = (JurySectionEntity.List) list;
            for (JurySectionEntity section : data) {
                int sectionFirstPosition = result.size();
                LineItem lineItem = new LineItem(sectionFirstPosition, true, section.getName());
                result.add(lineItem);
                if (section.getList() != null && !section.getList().isEmpty()) {
                    for (JuryEntity entity : section.getList()) {
                        result.add(new LineItem(sectionFirstPosition, false, entity));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_HEADER) {
            view = inflater.inflate(R.layout.header_row, parent, false);
            return new HeaderViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.jury_entity_row, parent, false);
            return new JuryAndOrgViewHolder(view, mOnItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        LineItem lineItem = (LineItem) getItem(position);
        GridSLM.LayoutParams lp = GridSLM.LayoutParams.from(holder.itemView.getLayoutParams());
        if (lineItem.isHeader) {
            lp.headerDisplay = LayoutManager.LayoutParams.HEADER_STICKY | LayoutManager.LayoutParams.HEADER_INLINE;
            lp.headerEndMarginIsAuto = true;
            lp.headerStartMarginIsAuto = true;
            ((HeaderViewHolder) holder).header.setText(lineItem.data.toString());
        } else {
            JuryAndOrgViewHolder jHolder = (JuryAndOrgViewHolder) holder;
            JuryEntity entity = (JuryEntity) lineItem.data;
            Context context = jHolder.itemView.getContext();
            Glide.with(context).load(entity.getImage()).into(jHolder.avatar);
            jHolder.title.setText(entity.getName());
            jHolder.city.setText(entity.getCity());
            jHolder.description.setText(Html.fromHtml(entity.getDescription()));
        }
        lp.setSlm(LinearSLM.ID);
        lp.setFirstPosition(lineItem.sectionFirstPosition);
        holder.itemView.setLayoutParams(lp);
    }

    public boolean isItemHeader(int position) {
        return ((LineItem) mData.get(position)).isHeader;
    }

    @Override
    public int getItemViewType(int position) {
        return isItemHeader(position) ? VIEW_TYPE_HEADER : VIEW_TYPE_CONTENT;
    }
}
