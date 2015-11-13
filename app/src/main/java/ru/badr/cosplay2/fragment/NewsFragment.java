package ru.badr.cosplay2.fragment;

import android.support.v4.view.PagerAdapter;

import ru.badr.base.fragment.BaseViewPagerFragment;
import ru.badr.cosplay2.adapter.NewsPagerAdapter;
import ru.badr.opencon.R;


/**
 * Created by ABadretdinov
 * 23.09.2015
 * 16:34
 */
public class NewsFragment extends BaseViewPagerFragment {
    @Override
    public int getLayoutId() {
        return R.layout.simple_viewpager_layout;
    }

    @Override
    public PagerAdapter getAdapter() {
        return new NewsPagerAdapter(getActivity(), getChildFragmentManager());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.news);
    }
}
