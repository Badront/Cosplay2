package ru.badr.cosplay2.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.util.BackListener;
import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.fragment.AboutFragment;
import ru.badr.cosplay2.fragment.FanArtPagerFragment;
import ru.badr.cosplay2.fragment.JuryAndOrgsFragment;
import ru.badr.cosplay2.fragment.MediaFragment;
import ru.badr.cosplay2.fragment.MembersList;
import ru.badr.cosplay2.fragment.NewsFragment;
import ru.badr.cosplay2.fragment.PhotoCosplayListFragment;
import ru.badr.cosplay2.fragment.RegulationsFragment;
import ru.badr.cosplay2.fragment.ScheduleFragment;
import ru.badr.cosplay2.fragment.SchemeFragment;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 16:13
 */
public class FragmentWrapperActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected View mMenuHeader;

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);
        switch (menuItem.getItemId()) {
            case R.id.schedule:
                if (!(fragment instanceof ScheduleFragment)) {
                    Navigate.toMain(this, ScheduleFragment.class, menuItem.getItemId());
                }
                break;
            case R.id.members:
                if (!(fragment instanceof MembersList)) {
                    Navigate.toMain(this, MembersList.class, menuItem.getItemId());
                }
                break;
            case R.id.photocosplay:
                if (!(fragment instanceof PhotoCosplayListFragment)) {
                    Navigate.toMain(this, PhotoCosplayListFragment.class, menuItem.getItemId());
                }
                break;
            case R.id.fanart:
                if (!(fragment instanceof FanArtPagerFragment)) {
                    Navigate.toMain(this, FanArtPagerFragment.class, menuItem.getItemId());
                }
                break;
            case R.id.jury:
                if (!(fragment instanceof JuryAndOrgsFragment)) {
                    Navigate.toMain(this, JuryAndOrgsFragment.class, menuItem.getItemId());
                }
                break;
            case R.id.media:
                if (!(fragment instanceof MediaFragment)) {
                    Navigate.toMain(this, MediaFragment.class, menuItem.getItemId());
                }
                break;
            case R.id.news:
                if (!(fragment instanceof NewsFragment)) {
                    Navigate.toMain(this, NewsFragment.class, menuItem.getItemId());
                }
                break;
            case R.id.scheme:
                if (!(fragment instanceof SchemeFragment)) {
                    Navigate.toMain(this, SchemeFragment.class);
                }
                break;
            case R.id.regulations:
                if (!(fragment instanceof RegulationsFragment)) {
                    Navigate.toMain(this, RegulationsFragment.class);
                }
                break;
            case R.id.about:
                if (!(fragment instanceof AboutFragment)) {
                    Navigate.toMain(this, AboutFragment.class);
                }
                break;
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
