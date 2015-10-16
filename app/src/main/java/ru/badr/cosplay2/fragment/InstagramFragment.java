package ru.badr.cosplay2.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ru.badr.base.fragment.RecyclerFragment;
import ru.badr.base.view.EndlessRecycleScrollListener;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.adapter.InstaFeedAdapter;
import ru.badr.cosplay2.adapter.viewholder.InstaFeedViewHolder;
import ru.badr.cosplay2.api.instagram.InstaFeed;
import ru.badr.cosplay2.api.instagram.InstaResult;
import ru.badr.cosplay2.task.InstaFeedRequest;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 16:30
 */
public class InstagramFragment extends RecyclerFragment<InstaFeed, InstaFeedViewHolder> implements RequestListener<InstaResult> {
    public static final String INSTAGRAM_PACKAGE = "com.instagram.android";
    private SpiceManager mSpiceManger = new SpiceManager(UncachedSpiceService.class);
    private Long mNextMaxFeedId;

    @Override
    public void onStart() {
        if (!mSpiceManger.isStarted()) {
            mSpiceManger.start(getActivity().getApplicationContext());
            onRefresh();
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if (mSpiceManger.isStarted()) {
            mSpiceManger.shouldStop();
        }
        super.onDestroy();
    }

    @Override
    public boolean isNeedDivider() {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getRecyclerView().addOnScrollListener(new EndlessRecycleScrollListener() {
            @Override
            public void onLoadMore() {
                loadFeed();
            }
        });
    }

    private void loadFeed() {
        setRefreshing(true);

        mSpiceManger.execute(new InstaFeedRequest(getActivity().getApplicationContext(), mNextMaxFeedId), this);
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        InstaFeed instaFeed = getAdapter().getItem(position);
        Uri uri = Uri.parse(instaFeed.getLink());
        Intent instaIntent = new Intent(Intent.ACTION_VIEW, uri);

        instaIntent.setPackage(INSTAGRAM_PACKAGE);

        try {
            startActivity(instaIntent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

    @Override
    public void onRefresh() {
        mNextMaxFeedId = null;
        loadFeed();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        reportError(spiceException.getLocalizedMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFeed();
            }
        });
    }

    @Override
    public void onRequestSuccess(InstaResult instaResult) {
        setRefreshing(false);
        if (instaResult != null) {
            if (mNextMaxFeedId == null || getAdapter() == null) {
                setAdapter(new InstaFeedAdapter(instaResult.getFeeds()));
            } else {
                ((InstaFeedAdapter) getAdapter()).addData(instaResult.getFeeds());
            }
            mNextMaxFeedId = instaResult.getPagination().getNextMaxTagId();
        }
    }
}
