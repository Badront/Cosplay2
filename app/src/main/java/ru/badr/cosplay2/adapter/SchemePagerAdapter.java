package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.badr.cosplay2.R;
import ru.badr.cosplay2.fragment.ImageFragment;

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
        StringBuilder path = new StringBuilder("file:///android_asset/scheme/");
        path.append(position).append(".png");
        ImageFragment fragment = new ImageFragment();
        fragment.setImagePath(path.toString());
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