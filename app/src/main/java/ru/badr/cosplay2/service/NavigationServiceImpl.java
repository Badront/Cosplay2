package ru.badr.cosplay2.service;

import android.support.v4.app.Fragment;

import ru.badr.base.activity.BaseActivity;
import ru.badr.base.service.NavigationService;
import ru.badr.cosplay2.activity.FragmentWrapperActivity;
import ru.badr.cosplay2.activity.MainActivity;
import ru.badr.cosplay2.fragment.AboutFragment;
import ru.badr.cosplay2.fragment.FanArtPagerFragment;
import ru.badr.cosplay2.fragment.JuryAndOrgsFragment;
import ru.badr.cosplay2.fragment.MediaFragment;
import ru.badr.cosplay2.fragment.MembersList;
import ru.badr.cosplay2.fragment.NewsFragment;
import ru.badr.cosplay2.fragment.PhotoCosplayListFragment;
import ru.badr.cosplay2.fragment.RulesFragment;
import ru.badr.cosplay2.fragment.ScheduleFragment;
import ru.badr.cosplay2.fragment.SchemeFragment;
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
        switch (resId) {
            case R.id.schedule:
                return ScheduleFragment.class;
            case R.id.members:
                return MembersList.class;
            case R.id.photocosplay:
                return PhotoCosplayListFragment.class;
            case R.id.fanart:
                return FanArtPagerFragment.class;
            case R.id.jury:
                return JuryAndOrgsFragment.class;
            case R.id.media:
                return MediaFragment.class;
            case R.id.news:
                return NewsFragment.class;
            case R.id.scheme:
                return SchemeFragment.class;
            case R.id.rules:
                return RulesFragment.class;
            default:
            case R.id.about:
                return AboutFragment.class;
        }
    }
}
