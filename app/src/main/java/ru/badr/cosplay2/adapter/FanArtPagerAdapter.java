package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.badr.cosplay2.R;
import ru.badr.cosplay2.fragment.FanArtListFragment;

/**
 * Created by Badr on 13.11.2015.
 */
public class FanArtPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public FanArtPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.fan_art_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        FanArtListFragment fragment = new FanArtListFragment();
        fragment.setTitle(getPageTitle(position));
        switch (position) {
            case 0:
                fragment.setTag("opencon.tag.traditional_fanart");
                break;
            case 1:
            default:
                fragment.setTag("opencon.tag.digital_fanart");
                break;
        }
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
