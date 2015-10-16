package ru.badr.base.service;

import android.util.Base64;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import ru.badr.base.util.json.UTCDateSerializer;

/**
 * Created by ABadretdinov
 * 24.06.2015
 * 18:53
 */
public class Base64ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password, Map<Type, Object> adapters) {
        // set endpoint url
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient());

        GsonBuilder gsonBuilder = new GsonBuilder();
        boolean needDefaultDateSerializer = true;
        if (adapters != null) {
            for (Type key : adapters.keySet()) {
                if (key == Date.class) {
                    needDefaultDateSerializer = false;
                }
                gsonBuilder.registerTypeAdapter(key, adapters.get(key));
            }
        }
        if (needDefaultDateSerializer) {
            gsonBuilder.registerTypeAdapter(Date.class, new UTCDateSerializer("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }
        builder.setConverter(new GsonConverter(gsonBuilder.create()));

        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {
                    // create Base64 encoder string
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Authorization", string);
                    request.addHeader("Accept", "application/json");
                }
            });
        }

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}