package com.histler.insta.remote;


import com.histler.insta.api.InstaResult;

import retrofit2.Call;
import retrofit2.http.GET;
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

}
