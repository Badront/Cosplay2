package ru.badr.cosplay2.service;

import android.support.v4.app.Fragment;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.service.NavigationService;
import ru.badr.cosplay2.activity.FragmentWrapperActivity;
import ru.badr.cosplay2.activity.MainActivity;
import ru.badr.cosplay2.fragment.AboutFragment;
import ru.badr.cosplay2.fragment.ScheduleFragment;
import ru.badr.cosplay2.util.Utils;
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
        if (Utils.isTimeHasCome()) {
            return R.id.about;
        } else {
            return R.id.schedule;
        }
    }

    @Override
    public int getNavigationMenuResId() {
        if (Utils.isTimeHasCome()) {
            return R.menu.lite_menu;
        } else {
            return R.menu.main_menu;
        }
    }

    @Override
    public Class<? extends Fragment> getMainFragment(int resId) {
        if (resId == R.id.about) {
            return AboutFragment.class;
        } else {
            return ScheduleFragment.class;
        }
    }
}
