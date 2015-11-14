package ru.badr.cosplay2.task;

import android.content.Context;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.instagram.InstaResult;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 15:44
 */
public class InstaFeedRequest extends TaskRequest<InstaResult> {
    private Long mNextMaxTagId;
    private Context mContext;

    public InstaFeedRequest(Context context, Long nextMaxTagId) {
        super(InstaResult.class);
        this.mContext = context;
        mNextMaxTagId = nextMaxTagId;
    }

    @Override
    public InstaResult loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        String clientId = container.getProperties().getProperty("instagram.client_id");
        String tag = container.getProperties().getProperty("global.tag.instagram");
        return container.getInstagramRestService().getRecent(tag, clientId, mNextMaxTagId);
    }
}
