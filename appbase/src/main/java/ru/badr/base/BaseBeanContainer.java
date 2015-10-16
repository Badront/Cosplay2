package ru.badr.base;


import ru.badr.base.service.NavigationService;

/**
 * Created by ABadretdinov
 * 24.06.2015
 * 16:17
 */
public class BaseBeanContainer {
    private static final Object MONITOR = new Object();
    private static BaseBeanContainer instance = null;
    private NavigationService navigationService;

    private BaseBeanContainer() {
    }

    public static BaseBeanContainer getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (MONITOR) {
            if (instance == null) {
                instance = new BaseBeanContainer();
            }
        }
        return instance;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }
}