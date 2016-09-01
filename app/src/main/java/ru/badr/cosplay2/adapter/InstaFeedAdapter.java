package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.histler.insta.api.InstaFeed;
import com.histler.insta.api.InstaImages;
import com.histler.insta.api.InstaUser;

import java.util.List;

import ru.badr.base.adapter.BasePageableRecyclerAdapter;
import ru.badr.cosplay2.adapter.viewholder.InstaFeedViewHolder;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 17:01
 */
public class InstaFeedAdapter extends BasePageableRecyclerAdapter<InstaFeed, InstaFeedViewHolder> {

    public InstaFeedAdapter(List<InstaFeed> data) {
        super(data);
    }

    @Override
    public InstaFeedViewHolder onCreateViewHolder(ViewGroup container, int viewType) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.insta_feed_row, container, false);
        return new InstaFeedViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(InstaFeedViewHolder holder, int position) {
        InstaFeed instaFeed = getItem(position);
        Context context = holder.itemView.getContext();
        InstaUser user = instaFeed.getUser();
        if (user != null) {
            Glide.with(context).load(user.getProfileImage()).into(holder.userPhoto);
            holder.userName.setText(user.getUsername());
        }
        holder.time.setText(DateUtils.getRelativeDateTimeString(context, instaFeed.getCreatedTime().getTime(), DateUtils.SECOND_IN_MILLIS, DateUtils.DAY_IN_MILLIS * 3, DateUtils.FORMAT_ABBREV_RELATIVE));
        if (instaFeed.getImages() != null) {
            InstaImages images = instaFeed.getImages();
            String imageUrl;
            if (images.getStandard() != null) {
                imageUrl = images.getStandard().getUrl();
            } else if (images.getLow() != null) {
                imageUrl = images.getLow().getUrl();
            } else {
                imageUrl = null;
            }
            if (!TextUtils.isEmpty(imageUrl)) {
                Glide.with(context).load(imageUrl).into(holder.photo);
            }
        }
        if (instaFeed.getTags() != null) {
            StringBuilder tagsBuilder = new StringBuilder("");
            for (int i = 0, size = instaFeed.getTags().size(); i < size; i++) {
                String tag = instaFeed.getTags().get(i);
                if (i != 0) {
                    tagsBuilder.append(", ");
                }
                tagsBuilder.append("#").append(tag);
            }
            holder.tags.setText(tagsBuilder.toString());
        }
        if (instaFeed.getCaption() != null) {
            holder.caption.setVisibility(View.VISIBLE);
            holder.caption.setText(instaFeed.getCaption().getText());
        } else {
            holder.caption.setVisibility(View.GONE);
        }

    }
}
