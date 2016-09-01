package com.histler.insta;

/**
 * Created by Badr
 * on 02.09.2016 1:01.
 */
public class InstaBeanContainer {

    private static final Object MONITOR = new Object();
    private static InstaBeanContainer sInstance = null;

    private InstaBeanContainer() {
    }

    public static InstaBeanContainer getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (MONITOR) {
            if (sInstance == null) {
                sInstance = new InstaBeanContainer();
            }
            return sInstance;
        }
    }


}
