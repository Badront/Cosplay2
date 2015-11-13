package ru.badr.cosplay2.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;

import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 23.09.2015
 * 17:04
 */
public class TweetViewHolder extends BaseViewHolder {
    public CardView holder;

    public TweetViewHolder(View itemView) {
        super(itemView);
    }

    public TweetViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView, clickListener);
    }

    @Override
    protected void initView(View itemView) {
        holder = (CardView) itemView.findViewById(R.id.holder);
    }
}
