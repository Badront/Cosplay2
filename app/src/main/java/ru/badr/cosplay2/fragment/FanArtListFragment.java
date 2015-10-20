package ru.badr.cosplay2.fragment;

import ru.badr.cosplay2.R;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 18:28
 */
public class FanArtListFragment extends FestSectionListFragment {
    @Override
    protected String getSectionPropertyTag() {
        return "opencon.tag.fanart";
    }

    @Override
    protected String getTitle() {
        return getString(R.string.fanart);
    }
}
