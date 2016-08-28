package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;

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
import ru.badr.cosplay2.adapter.viewholder.FestPhotoCardViewHolder;
import ru.badr.cosplay2.api.cards.Topic;
import ru.badr.cosplay2.api.cards.list.ListCard;
import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by Badr on 13.11.2015.
 */
public class MembersAdapter extends BaseRecyclerAdapter<Object, BaseViewHolder> implements SectionIndexer {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_CONTENT = 1;
    private LineItem[] mSections;

    public MembersAdapter(Topic.List data) {
        super(null);
        mData = setLineItems(data);
    }

    @Override
    public synchronized void setData(List data) {
        mData = setLineItems(data);
        notifyDataSetChanged();
    }

    private List<Object> setLineItems(List list) {
        List<LineItem> sections = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            Topic.List data = (Topic.List) list;
            for (Topic section : data) {
                int sectionFirstPosition = result.size();
                LineItem lineItem = new LineItem(sectionFirstPosition, true, section.getTitle());
                sections.add(lineItem);
                result.add(lineItem);
                if (section.getCards() != null && !section.getCards().isEmpty()) {
                    for (ListCard card : section.getCards()) {
                        result.add(new LineItem(sectionFirstPosition, false, card));
                    }
                }
            }
        }
        mSections = sections.toArray(new LineItem[sections.size()]);
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
            view = inflater.inflate(R.layout.photocosplay_row, parent, false);
            return new FestPhotoCardViewHolder(view, mOnItemClickListener);
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
            FestPhotoCardViewHolder jHolder = (FestPhotoCardViewHolder) holder;
            ListCard card = (ListCard) lineItem.data;
            Context context = jHolder.itemView.getContext();
            jHolder.title.setText(card.getVotingTitle());
            if (!TextUtils.isEmpty(card.getWin())) {
                jHolder.win.setVisibility(View.VISIBLE);
                int color;
                if ("1".equals(card.getWin())) {
                    color = R.color.gold;
                } else if ("2".equals(card.getWin())) {
                    color = R.color.silver;
                } else {
                    color = R.color.bronze;
                }
                jHolder.win.setColorFilter(context.getResources().getColor(color));
            } else {
                jHolder.win.setVisibility(View.GONE);
            }
            Glide.with(context).load(Utils.getCardImageUrl(context, card)).into(jHolder.photo);
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

    @Override
    public Object[] getSections() {
        return mSections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mSections[sectionIndex].sectionFirstPosition;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (getItemCount() == 0) {
            return 0;
        }
        if (position >= getItemCount()) {
            position = getItemCount() - 1;
        }
        LineItem item = (LineItem) getItem(position);
        for (int i = 0; i < mSections.length; i++) {
            LineItem section = mSections[i];
            if (section.sectionFirstPosition == item.sectionFirstPosition) {
                return i;
            }
        }
        return 0;
    }
}
