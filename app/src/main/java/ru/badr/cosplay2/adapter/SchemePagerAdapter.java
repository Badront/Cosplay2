package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.badr.cosplay2.fragment.ImageFragment;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 03.11.2015
 * 17:44
 */
public class SchemePagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public SchemePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.scheme_names);
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment fragment = new ImageFragment();
        fragment.setImagePath("scheme_" + (position + 1));
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}