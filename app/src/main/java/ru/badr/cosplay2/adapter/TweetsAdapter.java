package ru.badr.cosplay2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.List;

import ru.badr.base.adapter.BasePageableRecyclerAdapter;
import ru.badr.cosplay2.adapter.viewholder.TweetViewHolder;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 23.09.2015
 * 18:43
 */
public class TweetsAdapter extends BasePageableRecyclerAdapter<Tweet, TweetViewHolder> {
    public TweetsAdapter(List<Tweet> data) {
        super(data);
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup container, int viewType) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.tweet_row, container, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        Tweet tweet = getItem(position);
        holder.holder.removeAllViews();
        holder.holder.addView(new TweetView(holder.holder.getContext(), tweet));
    }
}
