package com.histler.insta.remote;

import com.histler.insta.api.InstaUserResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by abadretdinov
 * on 14.11.2018
 */
public interface InstagramUserRestApi {

    @GET("users/{user_id}/info/")
    Call<InstaUserResult> getUser(@Path("user_id") String complexUserId);
}
