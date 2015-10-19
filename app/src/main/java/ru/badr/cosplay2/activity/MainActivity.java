package ru.badr.cosplay2.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.util.BackListener;
import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.fragment.AboutFragment;
import ru.badr.cosplay2.fragment.FanArtListFragment;
import ru.badr.cosplay2.fragment.JuryAndOrgsFragment;
import ru.badr.cosplay2.fragment.MembersList;
import ru.badr.cosplay2.fragment.NewsFragment;
import ru.badr.cosplay2.fragment.PhotoCosplayListFragment;

/**
 * Created by ABadretdinov
 * 28.08.2015
 * 15:38
 */

//todo https://github.com/RubenGees/Introduction
//todo https://github.com/MiguelCatalan/MaterialSearchView
//todo http://cosplay2.ru/archive/2014
//todo http://opencon14.cosplay2.ru/cards
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected View mMenuHeader;
    private boolean mDoubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mNavigationView != null && mNavigationView.getMenu() != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
            mMenuHeader = mNavigationView.inflateHeaderView(R.layout.nav_header);
            MenuItem item = mNavigationView.getMenu().findItem(mNavigationService != null ? mNavigationService.getDefaultFragmentResId() : 0);
            if (item != null) {
                item.setChecked(true);
            }
        }
        if (mNavigationService != null) {
            initFragment();
        }
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
                if (mDoubleBackToExitPressedOnce) {
                    finish();
                } else {
                    mDoubleBackToExitPressedOnce = true;
                    Toast.makeText(this, getString(R.string.back_toast), Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            mDoubleBackToExitPressedOnce = false;

                        }
                    }, 2000);
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);
        switch (menuItem.getItemId()) {
            case R.id.members:
                if (!(fragment instanceof MembersList)) {
                    Navigate.toMain(this, MembersList.class);
                }
                break;
            case R.id.photocosplay:
                if (!(fragment instanceof PhotoCosplayListFragment)) {
                    Navigate.toMain(this, PhotoCosplayListFragment.class);
                }
                break;
            case R.id.fanart:
                if (!(fragment instanceof FanArtListFragment)) {
                    Navigate.toMain(this, FanArtListFragment.class);
                }
                break;
            case R.id.jury:
                if (!(fragment instanceof JuryAndOrgsFragment)) {
                    Navigate.toMain(this, JuryAndOrgsFragment.class);
                }
                break;
            case R.id.news:
                if (!(fragment instanceof NewsFragment)) {
                    Navigate.toMain(this, NewsFragment.class);
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
}
