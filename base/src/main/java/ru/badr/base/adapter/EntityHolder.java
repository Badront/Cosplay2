package ru.badr.base.adapter;

import android.view.View;
import android.widget.TextView;

import ru.badr.base.adapter.viewholder.BaseViewHolder;

/**
 * Created by ABadretdinov
 * 22.12.2014
 * 19:55
 */
public class EntityHolder extends BaseViewHolder {
    public TextView text1;

    public EntityHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected void initView(View itemView) {
        text1 = (TextView) itemView.findViewById(android.R.id.text1);
    }
}
