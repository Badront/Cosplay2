package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.badr.cosplay2.api.StringHolderFragment;
import ru.badr.opencon.R;

/**
 * Created by Badr
 * on 10.09.2016 19:48.
 */
public class RegulationsPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private int[] mTextResIds = new int[]{
            R.string.regulations_text,
            R.string.oferta_text
    };

    public RegulationsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.regulations_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        return StringHolderFragment.newInstance(mTextResIds[position]);
    }

    @Override
    public int getCount() {
        return mTextResIds.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
