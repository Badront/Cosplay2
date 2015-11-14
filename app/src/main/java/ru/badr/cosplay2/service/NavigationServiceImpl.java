package ru.badr.cosplay2.service;

import android.support.v4.app.Fragment;

import java.util.Calendar;
import java.util.Date;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.service.NavigationService;
import ru.badr.cosplay2.activity.FragmentWrapperActivity;
import ru.badr.cosplay2.activity.MainActivity;
import ru.badr.cosplay2.fragment.AboutFragment;
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
        return R.id.about;
    }

    @Override
    public int getNavigationMenuResId() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DATE, 20);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.YEAR, 2015);
        if (calendar.getTime().after(date)) {
            return R.menu.lite_menu;
        } else {
            return R.menu.main_menu;
        }
    }

    @Override
    public Class<? extends Fragment> getMainFragment(int resId) {
        return AboutFragment.class;
    }
}
