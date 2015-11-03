package ru.badr.cosplay2.fragment;

import android.support.v4.app.FragmentPagerAdapter;

import ru.badr.base.fragment.BaseViewPagerFragment;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.adapter.SchemePagerAdapter;

/**
 * Created by ABadretdinov
 * 03.11.2015
 * 17:43
 */
public class SchemeFragment extends BaseViewPagerFragment {
    @Override
    public int getLayoutId() {
        return R.layout.simple_viewpager_layout;
    }

    @Override
    public FragmentPagerAdapter getAdapter() {
        return new SchemePagerAdapter(getActivity(), getChildFragmentManager());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.scheme);
    }
}
