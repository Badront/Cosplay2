package ru.badr.cosplay2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.badr.base.fragment.BaseFragment;
import ru.badr.opencon.R;

/**
 * Created by Badr on 13.11.2015.
 */
public class RegulationsFragment extends BaseFragment {
    @Override
    protected String getTitle() {
        return getString(R.string.regulations);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.regulations_layout, container, false);
    }
}
