package ru.badr.base.service;

import android.util.Base64;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.badr.base.util.json.UTCDateSerializer;

/**
 * Created by ABadretdinov
 * 24.06.2015
 * 18:53
 */
public class Base64ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password, Map<Type, Object> adapters) {
        // set endpoint url
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl);

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
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;
            okBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    // create Base64 encoder string
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request = request.newBuilder()
                            .addHeader("Authorization", string)
                            .addHeader("Accept", "application/json")
                            .build();
                    return chain.proceed(request);
                }
            });
        }
        builder
                .client(okBuilder.build());

        Retrofit adapter = builder.build();

        return adapter.create(serviceClass);
    }
}