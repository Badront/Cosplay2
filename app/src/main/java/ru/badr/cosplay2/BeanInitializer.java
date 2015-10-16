package ru.badr.cosplay2;

import android.content.Context;

import ru.badr.base.BaseBeanContainer;
import ru.badr.cosplay2.service.NavigationServiceImpl;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 15:44
 */
public class BeanInitializer {
    private static final Object MONITOR = new Object();
    private static BeanInitializer instance = null;

    private BeanInitializer(Context context) {
        BaseBeanContainer beanContainer = BaseBeanContainer.getInstance();

        beanContainer.setNavigationService(new NavigationServiceImpl());
    }

    public static BeanInitializer getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        synchronized (MONITOR) {
            if (instance == null) {
                instance = new BeanInitializer(context);
            }
        }
        return instance;
    }
}
