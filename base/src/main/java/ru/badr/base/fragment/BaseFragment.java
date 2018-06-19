package ru.badr.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ru.badr.base.R;
import ru.badr.base.util.Navigate;

/**
 * Created by ABadretdinov
 * 29.06.2015
 * 11:53
 */
public abstract class BaseFragment extends Fragment {
    public static final int CREATE_ENTITY_CODE = 22;

    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void showMessage(String message, String actionMessage, View.OnClickListener action) {
        String resultMessage;
        if ("500 Internal Server Error".equals(message)) {
            resultMessage = getString(R.string.no_data_available_yet);
        } else if ("Network is not available".equals(message)) {
            resultMessage = getString(R.string.no_internet_connection);
        } else {
            resultMessage = message;
        }
        Snackbar.make(getView(), resultMessage, Snackbar.LENGTH_LONG).setAction(actionMessage, action).show();
    }

    public void showMessageWithAction(String message) {
        final Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(
                android.R.string.ok,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View root = getView();
        if (root != null) {
            Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
            if (toolbar != null) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.setSupportActionBar(toolbar);
                ActionBar ab = activity.getSupportActionBar();
                if (ab != null) {
                    boolean hasMenu =
                            getArguments() == null
                                    || getArguments().getBoolean(Navigate.PARAM_HAS_MENU, true);
                    if (hasMenu) {
                        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
                    }
                    ab.setDisplayHomeAsUpEnabled(true);
                    String title = getTitle();
                    if (title != null) {
                        ab.setTitle(title);
                    }
                    String subtitle = getSubTitle();
                    if (subtitle != null) {
                        ab.setSubtitle(subtitle);
                    }
                }
            }
        }
    }

    protected abstract String getTitle();

    protected String getSubTitle() {
        return null;
    }

    public void moveBack() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.setResult(Activity.RESULT_CANCELED);
            activity.finish();
        }
    }
}
