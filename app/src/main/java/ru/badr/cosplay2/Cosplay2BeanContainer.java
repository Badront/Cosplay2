package ru.badr.cosplay2;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.Properties;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import ru.badr.base.util.SettingsUtils;
import ru.badr.base.util.json.DateLongDeserializer;
import ru.badr.cosplay2.remote.InstagramRestService;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 17:28
 */
public class Cosplay2BeanContainer {

    private static final Object MONITOR = new Object();
    private static Cosplay2BeanContainer instance = null;
    private Properties properties;
    private RestAdapter instagramRestAdapter;
    private InstagramRestService instagramRestService;

    private Cosplay2BeanContainer(Context context) {
        properties = SettingsUtils.getAssetsProperties(context);
    }

    public static Cosplay2BeanContainer getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        synchronized (MONITOR) {
            if (instance == null) {
                instance = new Cosplay2BeanContainer(context);
            }
            return instance;
        }
    }

    public RestAdapter getInstagramRestAdapter() {
        if (instagramRestAdapter == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateLongDeserializer());
            instagramRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(properties.getProperty("instagram.url"))
                    .setConverter(new GsonConverter(gsonBuilder.create()))
                    .build();
        }
        return instagramRestAdapter;
    }

    public InstagramRestService getInstagramRestService() {
        if (instagramRestService == null) {
            instagramRestService = getInstagramRestAdapter().create(InstagramRestService.class);
        }
        return instagramRestService;
    }

    public Properties getProperties() {
        return properties;
    }

}
