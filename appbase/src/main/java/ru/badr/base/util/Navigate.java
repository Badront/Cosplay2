package ru.badr.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import ru.badr.base.BaseBeanContainer;
import ru.badr.base.activity.BaseActivity;
import ru.badr.base.fragment.EntityLoader;
import ru.badr.base.fragment.EntitySelectFragment;
import ru.badr.base.service.NavigationService;

/**
 * Created by ABadretdinov
 * 30.06.2015
 * 16:01
 */
public class Navigate {
    public static final String PARAM_CLASS = "class";
    public static final String PARAM_ARGS = "args";
    public static final String PARAM_HAS_MENU = "has_menu";
    public static final String PARAM_THEME = "theme";
    public static final String PARAM_FOR_SELECT = "for_select";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_EMPTYTEXT = "empty";
    public static final String PARAM_LOADER = "loader";
    public static final String PARAM_ID = "id";
    public static final String PARAM_ENTITY = "entity";


    public static void toMain(Context context, Class fragmentClass) {
        toMain(context, fragmentClass, null);
    }

    public static void toMain(Context context, Class fragmentClass, Bundle bundle) {
        toMain(context, fragmentClass, bundle, false);
    }

    public static void toMain(Context context, Class fragmentClass, Bundle bundle, boolean reOpen) {
        NavigationService navigationService = BaseBeanContainer.getInstance().getNavigationService();
        Class mainActivity = navigationService.getMainActivityClass();
        Intent intent = new Intent(context, mainActivity);
        intent.putExtra(PARAM_CLASS, fragmentClass.getName());
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtra(PARAM_ARGS, bundle);
        if (!reOpen && (context.getClass().equals(mainActivity))) {
            ((BaseActivity) context).initFragment(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    public static void to(Context context, Class fragmentClass, Bundle bundle, boolean hasMenu) {
        to(context, fragmentClass, bundle, hasMenu, 0);
    }

    public static void to(Context context, Class fragmentClass, Bundle bundle, boolean hasMenu, int theme) {
        NavigationService navigationService = BaseBeanContainer.getInstance().getNavigationService();
        Intent intent = new Intent(context, navigationService.getActivityClass());
        intent.putExtra(PARAM_CLASS, fragmentClass.getName());
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(PARAM_HAS_MENU, hasMenu);
        if (theme != 0) {
            intent.putExtra(PARAM_THEME, theme);
        }
        intent.putExtra(PARAM_ARGS, bundle);
        context.startActivity(intent);
    }

    public static void toForResult(Fragment fragment, Class fragmentClass, Bundle bundle, int requestCode) {
        toForResult(fragment, fragmentClass, bundle, requestCode, 0);
    }

    public static void toForResult(Fragment fragment, Class fragmentClass, Bundle bundle, int requestCode, int theme) {
        toForResult(fragment, fragmentClass, bundle, false, requestCode, theme);
    }

    public static void toForResult(Fragment fragment, Class fragmentClass, Bundle bundle, boolean hasMenu, int requestCode) {
        toForResult(fragment, fragmentClass, bundle, hasMenu, requestCode, 0);
    }

    public static void toForResult(Fragment fragment, Class fragmentClass, Bundle bundle, boolean hasMenu, int requestCode, int theme) {
        NavigationService navigationService = BaseBeanContainer.getInstance().getNavigationService();
        Intent intent = new Intent(fragment.getActivity(), navigationService.getActivityClass());
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(PARAM_HAS_MENU, hasMenu);
        if (theme != 0) {
            intent.putExtra(PARAM_THEME, theme);
        }
        intent.putExtra(PARAM_CLASS, fragmentClass.getName());
        intent.putExtra(PARAM_ARGS, bundle);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void toForEntityResult(Fragment fragment, Class fragmentClass, Bundle bundle, int requestCode) {
        toForEntityResult(fragment, fragmentClass, bundle, requestCode, 0);
    }

    public static void toForEntityResult(Fragment fragment, Class fragmentClass, Bundle bundle, int requestCode, int theme) {
        NavigationService navigationService = BaseBeanContainer.getInstance().getNavigationService();
        Intent intent = new Intent(fragment.getActivity(), navigationService.getActivityClass());
        intent.putExtra(PARAM_CLASS, fragmentClass.getName());
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(PARAM_FOR_SELECT, true);
        bundle.putBoolean(PARAM_HAS_MENU, false);
        if (theme != 0) {
            intent.putExtra(PARAM_THEME, theme);
        }
        intent.putExtra(PARAM_FOR_SELECT, true);
        intent.putExtra(PARAM_ARGS, bundle);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void toForEntityResult(Fragment fragment, String title, EntityLoader loader, int requestCode) {
        NavigationService navigationService = BaseBeanContainer.getInstance().getNavigationService();
        Intent intent = new Intent(fragment.getActivity(), navigationService.getActivityClass());
        intent.putExtra(PARAM_CLASS, EntitySelectFragment.class.getName());

        Bundle bundle = new Bundle();
        bundle.putBoolean(PARAM_FOR_SELECT, true);
        bundle.putString(PARAM_TITLE, title);
        bundle.putSerializable(PARAM_LOADER, loader);
        bundle.putBoolean(PARAM_HAS_MENU, false);

        intent.putExtra(PARAM_FOR_SELECT, true);
        intent.putExtra(PARAM_ARGS, bundle);

        fragment.startActivityForResult(intent, requestCode);
    }

    public static void toForEntityResult(Activity activity, Class fragmentClass, Bundle bundle, int requestCode) {
        NavigationService navigationService = BaseBeanContainer.getInstance().getNavigationService();
        Intent intent = new Intent(activity, navigationService.getActivityClass());
        intent.putExtra(PARAM_CLASS, fragmentClass.getName());
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(PARAM_FOR_SELECT, true);
        bundle.putBoolean(PARAM_HAS_MENU, false);
        intent.putExtra(PARAM_FOR_SELECT, true);
        intent.putExtra(PARAM_ARGS, bundle);
        activity.startActivityForResult(intent, requestCode);
    }
}
