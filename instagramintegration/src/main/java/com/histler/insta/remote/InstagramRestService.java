package com.histler.insta.remote;


import com.histler.insta.api.InstaResult;
import com.histler.insta.api.v2.InstaFirstResult;
import com.histler.insta.api.v2.InstaNodeResult;
import com.histler.insta.api.v2.InstaSecondResult;
import com.histler.insta.api.v2.node.InstaUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 14:46
 */
public interface InstagramRestService {
    @GET("/tags/{tag}/media/recent")
    Call<InstaResult> getRecent(@Path("tag") String tag, @Query("access_token") String clientId, @Query("max_tag_id") String maxTagId);

    @GET("/explore/tags/{tag}/?__a=1")
    Call<InstaFirstResult> getTagNodes(@Path("tag") String tag, @Query("max_id") String maxId);

    @GET("/query/")
    Call<InstaSecondResult> getNextTagNodes(@Query("q") String complexQuery);

    @GET("/query/?q=ig_user({userId}){id,username,profile_pic_url}")
    Call<InstaUser> getUserInfo(@Path("userId") String userId);

    @POST("/query/")
    @FormUrlEncoded
    @Headers({
            "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4",
            "Accept: */*",/*
            "Accept-Encoding: gzip, deflate, br",*/
            "Cache-Control: no-cache",
            "origin: https://www.instagram.com",
            "Pragma: no-cache",
            "x-instagram-ajax:1",
            "x-requested-with:XMLHttpRequest"
    })
    Call<InstaSecondResult> getTagNodes(@Field("q") String query, @Field("ref") String predefined, @Header("Referer") String referer);

    @GET("/p/{code}/?tagged={tag}&__a=1")
    Call<InstaNodeResult> getNodeInfo(@Path("code") String instaCode, @Path("tag") String tag);
}
