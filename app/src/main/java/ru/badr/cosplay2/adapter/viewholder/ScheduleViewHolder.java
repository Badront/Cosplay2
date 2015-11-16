package ru.badr.cosplay2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.opencon.R;

/**
 * Created by Badr on 16.11.2015.
 */
public class ScheduleViewHolder extends BaseViewHolder {
    public TextView time;
    public TextView title;
    public ImageView photo;

    public ScheduleViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView, clickListener);
    }

    @Override
    protected void initView(View itemView) {
        time = (TextView) itemView.findViewById(android.R.id.text1);
        title = (TextView) itemView.findViewById(android.R.id.text2);
        photo = (ImageView) itemView.findViewById(R.id.image);
    }
}
