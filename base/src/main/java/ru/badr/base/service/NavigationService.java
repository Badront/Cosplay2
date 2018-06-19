package ru.badr.base.service;

import android.support.v4.app.Fragment;

import ru.badr.base.activity.BaseActivity;


/**
 * Created by ABadretdinov
 * 29.06.2015
 * 11:07
 */
public interface NavigationService {
    Class<? extends BaseActivity> getMainActivityClass();

    Class<? extends BaseActivity> getActivityClass();

    int getDefaultFragmentResId();

    int getNavigationMenuResId();

    Class<? extends Fragment> getMainFragment(int resId);
}
