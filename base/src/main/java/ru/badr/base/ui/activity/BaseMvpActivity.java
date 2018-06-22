package ru.badr.base.ui.activity;

import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.badr.base.R;
import ru.badr.base.di.Injectable;
import ru.badr.base.presentation.BaseMvpView;
import ru.badr.base.ui.dialog.IDialogManager;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public abstract class BaseMvpActivity extends MvpAppCompatActivity implements BaseMvpView, Injectable {
    @Inject
    IDialogManager dialogManager;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        onViewsInjected();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        onViewsInjected();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        onViewsInjected();
    }

    /**
     * Requests given permission.
     * If the permission has been denied previously, a Dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    protected void requestPermission(final String permission, String requiresReason, String permissionName, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new MaterialDialog.Builder(this)
                    .title(getString(R.string.permission_title_rationale_template, permissionName))
                    .content(getString(R.string.permission_needed, requiresReason, permissionName))
                    .positiveText(R.string.dialog_ok)
                    .onPositive((dialog, which) -> {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(BaseMvpActivity.this,
                                new String[]{permission}, requestCode);
                    })
                    .negativeText(R.string.dialog_cancel)
                    .onNegative((dialog, which) -> dialog.dismiss())
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    protected void onViewsInjected() {

    }

    @Override
    public void close() {
        finish();
    }
}

