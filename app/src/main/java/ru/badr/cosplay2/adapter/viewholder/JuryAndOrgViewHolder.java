package ru.badr.cosplay2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.cosplay2.R;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 18:45
 */
public class JuryAndOrgViewHolder extends BaseViewHolder {
    public ImageView avatar;
    public TextView title;
    public TextView description;

    public JuryAndOrgViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView, clickListener);
    }

    @Override
    protected void initView(View itemView) {
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        title = (TextView) itemView.findViewById(android.R.id.text1);
        description = (TextView) itemView.findViewById(android.R.id.text2);
    }
}
