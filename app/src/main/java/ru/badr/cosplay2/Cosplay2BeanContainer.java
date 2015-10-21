package ru.badr.cosplay2;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import ru.badr.base.util.SettingsUtils;
import ru.badr.base.util.json.DateLongDeserializer;
import ru.badr.base.util.json.SimpleDateSerializer;
import ru.badr.cosplay2.remote.Cosplay2RestService;
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
    private RestAdapter cosplay2RestAdapter;
    private Cosplay2RestService cosplay2RestService;

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

    public RestAdapter getCosplay2RestAdapter() {
        if (cosplay2RestAdapter == null) {
            GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class, new SimpleDateSerializer(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
            cosplay2RestAdapter = new RestAdapter.Builder()
                    .setEndpoint(properties.getProperty("global.url"))
                    .setConverter(new GsonConverter(gsonBuilder.create()))
                    .build();
        }
        return cosplay2RestAdapter;
    }

    public Cosplay2RestService getCosplay2RestService() {
        if (cosplay2RestService == null) {
            cosplay2RestService = getCosplay2RestAdapter().create(Cosplay2RestService.class);
        }
        return cosplay2RestService;
    }

    public Properties getProperties() {
        return properties;
    }

}
