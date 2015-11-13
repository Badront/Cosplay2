package ru.badr.cosplay2.fragment;

import android.support.v4.view.PagerAdapter;

import ru.badr.base.fragment.BaseViewPagerFragment;
import ru.badr.cosplay2.adapter.FanArtPagerAdapter;
import ru.badr.opencon.R;

/**
 * Created by Badr on 13.11.2015.
 */
public class FanArtPagerFragment extends BaseViewPagerFragment {
    @Override
    public int getLayoutId() {
        return R.layout.simple_viewpager_layout;
    }

    @Override
    public PagerAdapter getAdapter() {
        return new FanArtPagerAdapter(getActivity(), getChildFragmentManager());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.fanart);
    }
}
