package com.badr.cosplay2.di;

import com.badr.cosplay2.BuildConfig;
import com.badr.cosplay2.data.network.ICosplay2Api;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.badr.base.di.BaseModule;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
@Module
public class Cosplay2Module {
    @Singleton
    @Provides
    ICosplay2Api provideCosplay2Api(Retrofit retrofit) {
        return retrofit.create(ICosplay2Api.class);
    }


    @Singleton
    @Provides
    @Named(BaseModule.BASE_URL)
    String provideBaseUrl() {
        return BuildConfig.COSPLAY2_URL;
    }

}
