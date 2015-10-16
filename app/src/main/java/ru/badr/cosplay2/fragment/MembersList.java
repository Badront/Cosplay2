package ru.badr.cosplay2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.badr.base.fragment.BaseFragment;
import ru.badr.cosplay2.R;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 15:47
 */
public class MembersList extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_fragment, container, false);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.members);
    }
}
