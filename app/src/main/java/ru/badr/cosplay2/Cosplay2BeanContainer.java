package ru.badr.cosplay2;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.histler.insta.remote.InstagramRestService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.badr.base.util.SettingsUtils;
import ru.badr.base.util.json.DateShortLongDeserializer;
import ru.badr.base.util.json.SimpleDateSerializer;
import ru.badr.cosplay2.remote.Cosplay2RestService;
import ru.badr.cosplay2.remote.VkRestService;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 17:28
 */
public class Cosplay2BeanContainer {

    private static final Object MONITOR = new Object();
    private static Cosplay2BeanContainer sInstance = null;
    private Properties properties;
    private Retrofit instagramRestAdapter;
    private InstagramRestService instagramRestService;
    private Retrofit cosplay2RestAdapter;
    private Cosplay2RestService cosplay2RestService;

    private Retrofit vkRestAdapter;
    private VkRestService vkRestService;

    private Cosplay2BeanContainer(Context context) {
        properties = SettingsUtils.getAssetsProperties(context);
    }

    public static Cosplay2BeanContainer getInstance(Context context) {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (MONITOR) {
            if (sInstance == null) {
                sInstance = new Cosplay2BeanContainer(context);
            }
            return sInstance;
        }
    }

    public Retrofit getInstagramRestAdapter() {
        if (instagramRestAdapter == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateShortLongDeserializer());
            instagramRestAdapter = new Retrofit.Builder()
                    .baseUrl(properties.getProperty("instagram.url"))
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
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

    public Retrofit getCosplay2RestAdapter() {
        if (cosplay2RestAdapter == null) {
            GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class, new SimpleDateSerializer(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
            cosplay2RestAdapter = new Retrofit.Builder()
                    .baseUrl(properties.getProperty("global.url16"))
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
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

    public Retrofit getVkRestAdapter() {
        if (vkRestAdapter == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            vkRestAdapter = new Retrofit.Builder()
                    .baseUrl(properties.getProperty("vk.url"))
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
        }
        return vkRestAdapter;
    }

    public VkRestService getVkRestService() {
        if (vkRestService == null) {
            vkRestService = getVkRestAdapter().create(VkRestService.class);
        }
        return vkRestService;
    }

    public Properties getProperties() {
        return properties;
    }

}
