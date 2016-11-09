package ru.badr.base.remote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Badr
 * on 11.09.2016 1:18.
 */
public interface TimeApiService {
    DateFormat TIME_API_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
    @GET("/utc/now")
    Call<ResponseBody> getCurrentTime();
}
