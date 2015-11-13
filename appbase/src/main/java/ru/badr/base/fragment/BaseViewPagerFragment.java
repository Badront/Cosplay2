package ru.badr.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.badr.base.R;

/**
 * Created by ABadretdinov
 * 29.06.2015
 * 12:15
 */
public abstract class BaseViewPagerFragment extends BaseFragment {
    protected ViewPager mViewPager;

    public int getLayoutId() {
        return R.layout.base_view_pager_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        return view;
    }

    public int getTabMode() {
        return TabLayout.MODE_FIXED;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager.setAdapter(getAdapter());
        View root = getView();
        if (root != null) {
            TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
            tabLayout.setTabMode(getTabMode());
            tabLayout.setupWithViewPager(mViewPager);
        }
    }

    public abstract PagerAdapter getAdapter();
}
