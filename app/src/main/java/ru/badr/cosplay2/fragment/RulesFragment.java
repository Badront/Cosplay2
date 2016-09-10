package ru.badr.cosplay2.fragment;

import android.support.v4.view.PagerAdapter;

import ru.badr.base.fragment.BaseViewPagerFragment;
import ru.badr.cosplay2.adapter.RegulationsPagerAdapter;
import ru.badr.opencon.R;

/**
 * Created by Badr on 13.11.2015.
 */
public class RulesFragment extends BaseViewPagerFragment {
    @Override
    public int getLayoutId() {
        return R.layout.rules_layout;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.rules);
    }

    @Override
    public PagerAdapter getAdapter() {
        return new RegulationsPagerAdapter(getActivity(), getChildFragmentManager());
    }
}
