package ru.badr.base.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.badr.base.SchedulersProvider;
import ru.badr.base.util.json.SimpleDateSerializer;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
@Module
public class BaseModule {
    //todo add analytics

    @Singleton
    @Provides
    CallAdapter.Factory provideCallAdapterFactory(SchedulersProvider schedulersProvider) {
        return RxJava2CallAdapterFactory.createWithScheduler(schedulersProvider.io());
    }

    @Singleton
    @Provides
    Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new SimpleDateSerializer(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())))
                .create();
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(Converter.Factory converterFactory,
                             CallAdapter.Factory callFactory) {
        return new Retrofit.Builder()
                .client(
                        new OkHttpClient.Builder().build()
                )
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callFactory)
                .build();
        //todo .baseUrl(BuildConfig.TIME_API_URL)
    }
}
