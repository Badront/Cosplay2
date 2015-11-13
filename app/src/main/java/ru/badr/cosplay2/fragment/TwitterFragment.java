package ru.badr.cosplay2.fragment;

import android.os.Bundle;
import android.view.View;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;

import ru.badr.base.fragment.RecyclerFragment;
import ru.badr.base.view.EndlessRecycleScrollListener;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.adapter.TweetsAdapter;
import ru.badr.cosplay2.adapter.viewholder.TweetViewHolder;

/**
 * Created by ABadretdinov
 * 23.09.2015
 * 16:58
 */
public class TwitterFragment extends RecyclerFragment<Tweet, TweetViewHolder> {
    private SearchTimeline mSearchTimeLine;
    private boolean mReloading = true;

    @Override
    public boolean isNeedDivider() {
        return false;
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {

    }

    @Override
    public void onRefresh() {
        mReloading = true;
        loadTweets();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String tag = Cosplay2BeanContainer.getInstance(getActivity()).getProperties().getProperty("global.tag");
        mSearchTimeLine = new SearchTimeline.Builder()
                .query("#" + tag)
                .build();
        getRecyclerView().addOnScrollListener(new EndlessRecycleScrollListener() {
            @Override
            public void onLoadMore() {
                mReloading = false;
                loadTweets();
            }
        });
        onRefresh();
    }

    private void loadTweets() {
        setRefreshing(true);

        Long offset = null;
        if (!mReloading && getAdapter() != null) {
            Tweet tweet = getAdapter().getItem(getAdapter().getItemCount() - 1);
            offset = tweet.getId();
        }
        mSearchTimeLine.previous(offset, new Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                setRefreshing(false);
                TimelineResult<Tweet> data = result.data;
                if (data != null) {
                    if (!mReloading && getAdapter() != null) {
                        ((TweetsAdapter) getAdapter()).addData(data.items);
                    } else {
                        setAdapter(new TweetsAdapter(data.items));
                    }
                }
            }

            @Override
            public void failure(TwitterException e) {
                setRefreshing(false);
                setAdapter(new TweetsAdapter(null));
                showMessage(e.getMessage());
            }
        });
    }
}
