package ru.badr.base;


import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import ru.badr.base.remote.TimeApiService;
import ru.badr.base.service.NavigationService;
import ru.badr.base.util.json.DateShortLongDeserializer;

/**
 * Created by ABadretdinov
 * 24.06.2015
 * 16:17
 */
public class BaseBeanContainer {
    private static final Object MONITOR = new Object();
    private static BaseBeanContainer sInstance = null;
    private NavigationService mNavigationService;

    private TimeApiService mTimeApiService;

    private BaseBeanContainer() {
    }

    public static BaseBeanContainer getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (MONITOR) {
            if (sInstance == null) {
                sInstance = new BaseBeanContainer();
            }
        }
        return sInstance;
    }

    public NavigationService getNavigationService() {
        return mNavigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        mNavigationService = navigationService;
    }

    public TimeApiService getTimeApiService() {
        if (mTimeApiService == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateShortLongDeserializer());
            Retrofit timeApiRetrofit = new Retrofit.Builder()
                    .baseUrl("http://www.timeapi.org/")
                    .build();
            mTimeApiService = timeApiRetrofit.create(TimeApiService.class);
        }
        return mTimeApiService;
    }
}