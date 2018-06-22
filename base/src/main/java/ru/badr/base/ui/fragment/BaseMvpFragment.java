package ru.badr.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.badr.base.di.Injectable;
import ru.badr.base.presentation.BaseMvpView;
import ru.badr.base.ui.activity.BaseMvpActivity;
import ru.badr.base.ui.dialog.IDialogManager;
import ru.badr.base.util.BackListener;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public abstract class BaseMvpFragment extends MvpAppCompatFragment implements BackListener, BaseMvpView, Injectable {
    @Inject
    IDialogManager dialogManager;
    private Unbinder unbinder;

    /**
     * Use this to provide fragments layout
     *
     * @return layout res id for this fragment
     */
    @LayoutRes
    public abstract int getLayoutId();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }

    @Override
    public void close() {
        ((BaseMvpActivity) getActivity()).close();
    }
}
