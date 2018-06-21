package ru.badr.cosplay2.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.badr.cosplay2.model.media.AlbumsAndPhotos;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ru.badr.base.BaseSpiceManager;
import ru.badr.base.fragment.BaseViewPagerFragment;
import ru.badr.cosplay2.adapter.MediaPagerAdapter;
import ru.badr.cosplay2.task.AlbumsAndPhotosLoadRequest;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 15:28
 */
public class MediaFragment extends BaseViewPagerFragment implements SwipeRefreshLayout.OnRefreshListener, RequestListener<AlbumsAndPhotos> {
    private SpiceManager mSpiceManager = new BaseSpiceManager(UncachedSpiceService.class);
    private MediaPagerAdapter mAdapter;

    @Override
    public void onStart() {
        if (!mSpiceManager.isStarted()) {
            mSpiceManager.start(getActivity().getApplicationContext());
            onRefresh();
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if (mSpiceManager.isStarted()) {
            mSpiceManager.shouldStop();
        }
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.simple_viewpager_layout;
    }

    @Override
    public PagerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new MediaPagerAdapter(getActivity(), getChildFragmentManager(), this);
        }
        return mAdapter;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.media);
    }

    @Override
    public void onRefresh() {
        mSpiceManager.execute(new AlbumsAndPhotosLoadRequest(getActivity()), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        ((MediaPagerAdapter) getAdapter()).setAlbumsAndPhotos(null);
        showMessage(spiceException.getCause() != null ? spiceException.getCause().getMessage() : spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(AlbumsAndPhotos albumsAndPhotos) {
        ((MediaPagerAdapter) getAdapter()).setAlbumsAndPhotos(albumsAndPhotos);
    }
}
