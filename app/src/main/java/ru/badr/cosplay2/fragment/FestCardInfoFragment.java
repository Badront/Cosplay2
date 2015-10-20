package ru.badr.cosplay2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;

import ru.badr.base.fragment.BaseFragment;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 11:07
 */
public class FestCardInfoFragment extends BaseFragment {
    public static final String TITLE = "title";
    public static final String CARD = "card";
    private SpiceManager mSpiceManager = new SpiceManager(UncachedSpiceService.class);

    @Override
    protected String getTitle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getString(TITLE);
        }/*topic.title*/
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mSpiceManager.isStarted()) {
            mSpiceManager.start(getActivity().getApplicationContext());
            //todo load
        }
    }

    @Override
    public void onDestroy() {
        if (mSpiceManager.isStarted()) {
            mSpiceManager.shouldStop();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
