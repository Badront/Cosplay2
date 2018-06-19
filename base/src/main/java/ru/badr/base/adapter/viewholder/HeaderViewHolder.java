package ru.badr.base.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

/**
 * Created by ABadretdinov
 * 10.07.2015
 * 18:05
 */
public class HeaderViewHolder extends BaseViewHolder {
    public TextView header;

    public HeaderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        header = (TextView) itemView.findViewById(android.R.id.text1);
    }
}
