package ru.badr.cosplay2.fragment;

import android.view.View;

import ru.badr.cosplay2.R;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 18:26
 */
public class PhotoCosplayListFragment extends FestSectionListFragment {
    @Override
    protected String getSectionPropertyTag() {
        return "opencon.tag.photo";
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {

    }


    @Override
    protected String getTitle() {
        return getString(R.string.photocosplay);
    }
}
