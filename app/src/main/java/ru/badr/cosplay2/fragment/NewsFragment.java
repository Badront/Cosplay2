package ru.badr.cosplay2.fragment;

import android.support.v4.app.FragmentPagerAdapter;

import ru.badr.base.fragment.BaseViewPagerFragment;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.adapter.NewsPagerAdapter;


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
    public FragmentPagerAdapter getAdapter() {
        return new NewsPagerAdapter(getActivity(), getChildFragmentManager());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.news);
    }
}
