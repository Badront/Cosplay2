package ru.badr.cosplay2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.util.BackListener;
import ru.badr.base.util.Navigate;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 16:13
 */
public class FragmentWrapperActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected View mMenuHeader;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);
        Class newFragmentClass = mNavigationService.getMainFragment(menuItem.getItemId());
        if (fragment == null || !fragment.getClass().equals(newFragmentClass)) {
            Navigate.toMain(this, newFragmentClass, menuItem.getItemId());
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mNavigationView != null && mNavigationView.getMenu() != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
            mMenuHeader = mNavigationView.inflateHeaderView(R.layout.nav_header);
            int resId;
            if (getIntent() != null && getIntent().hasExtra(Navigate.PARAM_MENU_ID)) {
                resId = getIntent().getIntExtra(Navigate.PARAM_MENU_ID, 0);
            } else {
                resId = mNavigationService != null ? mNavigationService.getDefaultFragmentResId() : 0;
            }
            MenuItem item = mNavigationView.getMenu().findItem(resId);
            if (item != null) {
                item.setChecked(true);
            }
        }
        if (mNavigationService != null) {
            init();
        }
    }

    private void init() {
        if (getIntent() != null && getIntent().hasExtra(Navigate.PARAM_ARGS)) {
            Bundle bundle = getIntent().getBundleExtra(Navigate.PARAM_ARGS);
            mHasMenu = bundle.getBoolean(Navigate.PARAM_HAS_MENU, true);
        }
        initFragment();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if (
                    currentFragment == null
                            || !(currentFragment instanceof BackListener)
                            || !((BackListener) currentFragment).onBackPressed()) {
                super.onBackPressed();
            }
        }
    }

}
