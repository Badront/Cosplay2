package ru.badr.cosplay2.api;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.badr.opencon.R;

/**
 * Created by Badr
 * on 10.09.2016 19:59.
 */
public class StringHolderFragment extends Fragment {
    private static final String TEXT_RES_ID = "textResId";

    public static StringHolderFragment newInstance(int textResId) {
        StringHolderFragment fragment = new StringHolderFragment();
        Bundle args = new Bundle();
        args.putInt(TEXT_RES_ID, textResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.string_holder_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int textResId = getArguments().getInt(TEXT_RES_ID);
        ((TextView) view.findViewById(R.id.text_holder)).setText(textResId);
    }
}
