package ru.badr.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.badr.base.di.Injectable;
import ru.badr.base.presentation.BaseMvpView;
import ru.badr.base.ui.dialog.IDialogManager;
import ru.badr.base.util.BackListener;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public abstract class BaseMvpDialogFragment extends MvpAppCompatDialogFragment implements BackListener, BaseMvpView, Injectable {
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
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void close() {
        dismissAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }
}
