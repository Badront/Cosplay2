package ru.badr.cosplay2.task;

import android.content.Context;

import com.histler.insta.api.InstaResult;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 15:44
 */
public class InstaFeedRequest extends TaskRequest<InstaResult> {
    private String mNextMaxTagId;
    private Context mContext;

    public InstaFeedRequest(Context context, String nextMaxTagId) {
        super(InstaResult.class);
        this.mContext = context;
        mNextMaxTagId = nextMaxTagId;
    }

    @Override
    public InstaResult loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        String accessToken = container.getProperties().getProperty("instagram.access_token");
        String tag = container.getProperties().getProperty("global.tag.instagram");
        return container.getInstagramRestService().getRecent(tag, accessToken, mNextMaxTagId).execute().body();
    }
}
