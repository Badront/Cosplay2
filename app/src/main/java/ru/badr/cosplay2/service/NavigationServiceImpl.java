package ru.badr.cosplay2.service;

import android.support.v4.app.Fragment;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.service.NavigationService;
import ru.badr.cosplay2.activity.FragmentWrapperActivity;
import ru.badr.cosplay2.activity.MainActivity;
import ru.badr.cosplay2.fragment.JuryAndOrgsFragment;
import ru.badr.opencon.R;


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
        return FragmentWrapperActivity.class;
    }

    @Override
    public int getDefaultFragmentResId() {
        return R.id.jury;
    }

    @Override
    public int getNavigationMenuResId() {
        return R.menu.main_menu;
    }

    @Override
    public Class<? extends Fragment> getMainFragment(int resId) {
        return JuryAndOrgsFragment.class;
    }
}
