package ru.badr.base.remote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Badr
 * on 11.09.2016 1:18.
 */
public interface TimeApiService {
    @GET("/utc/now")
    Call<ResponseBody> getCurrentTime();
}
