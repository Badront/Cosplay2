package ru.badr.cosplay2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 18:45
 */
public class JuryAndOrgViewHolder extends BaseViewHolder {
    public ImageView avatar;
    public TextView title;
    public TextView city;
    public TextView description;

    public JuryAndOrgViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView, clickListener);
    }

    @Override
    protected void initView(View itemView) {
        avatar = itemView.findViewById(R.id.avatar);
        title = itemView.findViewById(android.R.id.text1);
        city = itemView.findViewById(android.R.id.text2);
        description = itemView.findViewById(R.id.description);
    }
}
