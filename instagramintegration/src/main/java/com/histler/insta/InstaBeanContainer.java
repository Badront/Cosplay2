package com.histler.insta;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.histler.insta.remote.InstagramRestService;
import com.histler.insta.remote.InstagramUserRestApi;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.badr.base.util.json.DateShortLongDeserializer;

/**
 * Created by Badr
 * on 02.09.2016 1:01.
 */
public class InstaBeanContainer {

    private static final Object MONITOR = new Object();
    private static final String TOKEN = "csrftoken";
    private static final String SESSION_ID = "sessionid";
    private static InstaBeanContainer sInstance = null;
    private InstagramRestService instagramRestService;
    private InstagramUserRestApi instagramUserRestApi;
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

    public InstagramUserRestApi getInstagramUserRestApi() {
        if (instagramUserRestApi == null) {
            instagramRestService = re
        }
    }

    public InstagramRestService getInstagramRestService() {
        if (instagramRestService == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateShortLongDeserializer());
            Retrofit retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor() {
                                String token=null;
                                String sessionId=null;
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request=chain.request();
                                    if(token!=null&&sessionId!=null){
                                        request=request.newBuilder()
                                                .addHeader("x-csrftoken", token)
                                                .addHeader("Cookie", sessionId)
                                                .build();
                                    }
                                    Response response = chain.proceed(request);
                                    Headers headers = response.headers();
                                    List<String> cookies = headers.values("set-cookie");
                                    if (cookies != null) {
                                        boolean cookieSet = false, tokenSet = false;
                                        for (String cookie : cookies) {
                                            if (cookie.contains(TOKEN)) {
                                                token = getCookieValue(cookie, TOKEN, false);
                                                tokenSet = true;
                                            } else if (cookie.contains(SESSION_ID)) {
                                                sessionId = getCookieValue(cookie, SESSION_ID, true);
                                                cookieSet = true;
                                            }
                                            if (tokenSet && cookieSet) {
                                                sessionId += "; " + TOKEN + "=" + token;
                                                break;
                                            }
                                        }
                                    }
                                    return response;
                                }

                                @NonNull
                                private String getCookieValue(String cookie, String valueName, boolean withName) {
                                    String temp;
                                    String tokenParam;
                                    temp = cookie.substring(cookie.indexOf(valueName) + (withName ? 0 : (valueName.length() + 1)));
                                    if (temp.contains(";")) {
                                        temp = temp.substring(0, temp.indexOf(";"));
                                    }
                                    tokenParam = temp;
                                    return tokenParam;
                                }
                            })
                            .build())
                    .baseUrl("https://www.instagram.com/")
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
            instagramRestService = retrofit.create(InstagramRestService.class);
        }
        return instagramRestService;
    }
}
