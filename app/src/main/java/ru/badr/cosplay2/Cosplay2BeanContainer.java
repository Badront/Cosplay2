package ru.badr.cosplay2;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.histler.insta.remote.InstagramRestService;
import com.histler.insta.remote.InstagramUserRestApi;

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
    private InstagramRestService instagramRestService;
    private InstagramUserRestApi instagramUserRestApi;
    private Cosplay2RestService cosplay2RestService;

    private VkRestService vkRestService;

    private Cosplay2BeanContainer(Context context) {
        properties = SettingsUtils.getRawProperties(context);
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

    public InstagramRestService getInstagramRestService() {
        if (instagramRestService == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateShortLongDeserializer());
            Retrofit instagramRestAdapter = new Retrofit.Builder()
                    .baseUrl(properties.getProperty("instagram.url"))
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
            instagramRestService = instagramRestAdapter.create(InstagramRestService.class);
        }
        return instagramRestService;
    }

    public Cosplay2RestService getCosplay2RestService() {
        if (cosplay2RestService == null) {
            GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class, new SimpleDateSerializer(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
            Retrofit cosplay2RestAdapter = new Retrofit.Builder()
                    .baseUrl(properties.getProperty("global.url18"))
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
            cosplay2RestService = cosplay2RestAdapter.create(Cosplay2RestService.class);
        }
        return cosplay2RestService;
    }

    public VkRestService getVkRestService() {
        if (vkRestService == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Retrofit vkRestAdapter = new Retrofit.Builder()
                    .baseUrl(properties.getProperty("vk.url"))
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
            vkRestService = vkRestAdapter.create(VkRestService.class);
        }
        return vkRestService;
    }

    public Properties getProperties() {
        return properties;
    }

}
