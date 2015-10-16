package ru.badr.cosplay2.service;

import android.support.v4.app.Fragment;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.service.NavigationService;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.activity.MainActivity;
import ru.badr.cosplay2.fragment.NewsFragment;


/**
 * Created by ABadretdinov
 * 28.08.2015
 * 16:30
 */
public class NavigationServiceImpl implements NavigationService {
    @Override
    public Class<? extends BaseActivity> getMainActivityClass() {
        return MainActivity.class;
    }

    @Override
    public Class<? extends BaseActivity> getActivityClass() {
        return MainActivity.class;
    }

    @Override
    public int getDefaultFragmentResId() {
        return R.id.news;
    }

    @Override
    public int getNavigationMenuResId() {
        return R.menu.main_menu;
    }

    @Override
    public Class<? extends Fragment> getMainFragment(int resId) {
        return NewsFragment.class;
    }
}
