package com.histler.insta.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.histler.insta.R;
import com.histler.insta.adapter.viewholder.InstaFeedViewHolder;
import com.histler.insta.api.v2.node.InstaNode;
import com.histler.insta.api.v2.node.InstaUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.badr.base.adapter.BasePageableRecyclerAdapter;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 17:01
 */
public class InstaFeedAdapter extends BasePageableRecyclerAdapter<InstaNode, InstaFeedViewHolder> {
    private Map<String, InstaUser> mUsersMap = new HashMap<>();
    private Map<String, List<Integer>> mWaitList = new HashMap<>();

    public InstaFeedAdapter(List<InstaNode> data) {
        super(data);
        for (InstaNode instaNode : data) {
            if (!mWaitList.containsKey(instaNode.getOwner().getId())) {
                mWaitList.put(instaNode.getOwner().getId(), new ArrayList<Integer>());
            }
        }
    }

    public void updateUser(InstaUser instaUser) {
        mUsersMap.put(instaUser.getId(), instaUser);

        if (mWaitList.containsKey(instaUser.getId())) {
            List<Integer> positions = mWaitList.get(instaUser.getId());
            mWaitList.remove(instaUser.getId());
            for (Integer position : positions) {
                notifyItemChanged(position);
            }
        }
    }

    public Set<String> getWaitIds() {
        return mWaitList.keySet();
    }

    @Override
    public InstaFeedViewHolder onCreateViewHolder(ViewGroup container, int viewType) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.insta_feed_row, container, false);
        return new InstaFeedViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(InstaFeedViewHolder holder, int position) {
        InstaNode instaFeed = getItem(position);
        Context context = holder.itemView.getContext();
        InstaUser user = instaFeed.getOwner();
        if (user.getAvatar() == null) {
            if (mUsersMap.containsKey(user.getId())) {
                instaFeed.setOwner(mUsersMap.get(user.getId()));
            } else {
                if (!mWaitList.containsKey(user.getId())) {
                    mWaitList.put(user.getId(), new ArrayList<Integer>());
                }
                mWaitList.get(user.getId()).add(position);
            }
        }
        if (user.getAvatar() != null) {
            Glide.with(context).load(user.getAvatar()).into(holder.userPhoto);
            holder.userName.setText(user.getUsername());
        } else {
            holder.userPhoto.setImageResource(0);
            holder.userName.setText(null);
        }
        holder.time.setText(
                DateUtils.getRelativeDateTimeString(
                        context,
                        instaFeed.getCreatedTime().getTime(),
                        DateUtils.SECOND_IN_MILLIS,
                        DateUtils.DAY_IN_MILLIS * 3,
                        DateUtils.FORMAT_ABBREV_RELATIVE));


        String imageUrl;
        if (instaFeed.getImagePath() != null) {
            imageUrl = instaFeed.getImagePath();
        } else if (instaFeed.getThumbnailPath() != null) {
            imageUrl = instaFeed.getThumbnailPath();
        } else {
            imageUrl = null;
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide
                    .with(context)
                    .load(imageUrl)
                    .into(holder.photo);
        }
        holder.tags.setText(instaFeed.getDescription());
        if (instaFeed.getDescription() != null) {
            holder.caption.setVisibility(View.VISIBLE);
            holder.caption.setText(instaFeed.getDescription());
        } else {
            holder.caption.setVisibility(View.GONE);
        }
        if (instaFeed.isVideo()) {
            holder.isVideo.setVisibility(View.VISIBLE);
        } else {
            holder.isVideo.setVisibility(View.GONE);
        }
    }
}
