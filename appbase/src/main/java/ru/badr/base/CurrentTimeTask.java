package ru.badr.base;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.badr.base.remote.TimeApiService;
import ru.badr.base.util.NetworkUtils;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 11.09.2016 1:50.
 */
public class CurrentTimeTask extends TaskRequest<Date> {
    private Context mContext;

    public CurrentTimeTask(Context context) {
        super(Date.class);
        mContext = context.getApplicationContext();
    }

    @Override
    public Date loadData() throws Exception {
        if (NetworkUtils.isNetworkAvailable(mContext)) {
            TimeApiService service = BaseBeanContainer.getInstance().getTimeApiService();
            Response<ResponseBody> response = service.getCurrentTime().execute();
            if (response.isSuccessful()) {
                String dateString = response.body().string();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");//2016-09-10T23:20:10+01:00
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(format.parse(dateString));
                calendar.setTimeZone(TimeZone.getDefault());
                return calendar.getTime();
            }
        }
        return new Date();
    }
}