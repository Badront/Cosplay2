package ru.badr.cosplay2.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.view.CircleImageView;
import ru.badr.opencon.R;


/**
 * Created by ABadretdinov
 * 25.09.2015
 * 16:30
 */
public class InstaFeedViewHolder extends BaseViewHolder {
    public CircleImageView userPhoto;
    public TextView userName;
    public TextView time;
    public ImageView photo;
    public TextView tags;
    public TextView caption;

    public InstaFeedViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView, clickListener);
    }

    @Override
    protected void initView(View itemView) {
        userPhoto = (CircleImageView) itemView.findViewById(R.id.user_image);
        userName = (TextView) itemView.findViewById(R.id.username);
        time = (TextView) itemView.findViewById(R.id.time);
        photo = (ImageView) itemView.findViewById(R.id.image);
        caption = (TextView) itemView.findViewById(R.id.caption);
        tags = (TextView) itemView.findViewById(R.id.tags);
    }
}
