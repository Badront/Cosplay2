package com.histler.cosplay2.remote.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.histler.base.remote.serializer.DateFormatSerializer
import com.histler.cosplay2.data.repository.UrlProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by abadretdinov
 * on 08.08.2018
 */
class Cosplay2ScheduleServiceFactory {

    open fun makeCosplay2ScheduleService(isDebug: Boolean, urlProvider: UrlProvider): Cosplay2ScheduleService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug)
        )
        return makeCosplay2ScheduleService(
                okHttpClient,
                GsonBuilder()
                        .registerTypeAdapter(Date::class.java, DateFormatSerializer(SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())))
                        .create(),
                urlProvider)
    }

    private fun makeCosplay2ScheduleService(okHttpClient: OkHttpClient, gson: Gson, urlProvider: UrlProvider): Cosplay2ScheduleService {
        val retrofit = Retrofit.Builder()
                .baseUrl(urlProvider.getBaseFestivalUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(Cosplay2ScheduleService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}