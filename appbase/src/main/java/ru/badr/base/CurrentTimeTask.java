package ru.badr.base;

import android.content.Context;

import com.octo.android.robospice.retry.DefaultRetryPolicy;

import java.util.Calendar;
import java.util.Date;

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
        setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
    }

    @Override
    public Date loadData() throws Exception {
        if (NetworkUtils.isNetworkAvailable(mContext)) {
            TimeApiService service = BaseBeanContainer.getInstance().getTimeApiService();
            Response<ResponseBody> response = service.getCurrentTime().execute();
            if (response.isSuccessful()) {
                String dateString = response.body().string();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(TimeApiService.TIME_API_FORMAT.parse(dateString));
                return calendar.getTime();
            }
        }
        return new Date();
    }
}
