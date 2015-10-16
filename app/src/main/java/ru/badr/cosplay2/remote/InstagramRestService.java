package ru.badr.cosplay2.remote;


import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import ru.badr.cosplay2.api.instagram.InstaResult;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 14:46
 */
public interface InstagramRestService {
    @GET("/tags/{tag}/media/recent")
    InstaResult getRecent(@Path("tag") String tag, @Query("client_id") String clientId, @Query("max_tag_id") Long maxTagId);

}
