package ru.badr.cosplay2.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import ru.badr.base.adapter.viewholder.BaseViewHolder;

/**
 * Created by Badr on 16.11.2015.
 */
public class ScheduleSectionViewHolder extends BaseViewHolder {
    public TextView header;
    public TextView time;

    public ScheduleSectionViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        header = (TextView) itemView.findViewById(android.R.id.text1);
        time = (TextView) itemView.findViewById(android.R.id.text2);
    }
}
