package com.histler.insta;

import com.google.gson.GsonBuilder;
import com.histler.insta.remote.InstagramRestService;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.badr.base.util.json.DateShortLongDeserializer;

/**
 * Created by Badr
 * on 02.09.2016 1:01.
 */
public class InstaBeanContainer {

    private static final Object MONITOR = new Object();
    private static InstaBeanContainer sInstance = null;
    private InstagramRestService instagramRestService;

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

    public InstagramRestService getInstagramRestService() {
        if (instagramRestService == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateShortLongDeserializer());
            Retrofit retrofit = new Retrofit.Builder()
                    /*.client(new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor() {
                                String token=null;
                                String sessionId=null;
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request=chain.request();
                                    if(token!=null&&sessionId!=null){
                                        request=request.newBuilder()
                                                .addHeader()
                                    }
                                    Response response=chain.proceed(chain.request());
                                    if(count==1) {
                                        Log.d("retrofit response", response.body().string());
                                    }else {
                                        count++;
                                    }
                                    return response;
                                }
                            })
                    .build())*/
                    .baseUrl("https://www.instagram.com/")
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
            instagramRestService = retrofit.create(InstagramRestService.class);
        }
        return instagramRestService;
    }
}
