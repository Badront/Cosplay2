package com.badr.cosplay2.di;

import com.badr.cosplay2.data.network.ICosplay2Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
@Module
public class Cosplay2sModule {
    @Singleton
    @Provides
    ICosplay2Api provideCosplay2Api(Retrofit retrofit) {
        return retrofit.create(ICosplay2Api.class);
    }


}
