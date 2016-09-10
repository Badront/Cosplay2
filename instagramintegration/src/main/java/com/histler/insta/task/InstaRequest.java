package com.histler.insta.task;

import com.histler.insta.InstaBeanContainer;
import com.histler.insta.api.v2.InstaFirstResult;
import com.histler.insta.api.v2.InstaMedia;
import com.histler.insta.remote.InstagramRestService;

import retrofit2.Call;
import retrofit2.Response;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 02.09.2016 2:13.
 */
public class InstaRequest extends TaskRequest<InstaMedia> {
    private String mTag;
    private String mMaxId;


    public InstaRequest(String tag, String maxId) {
        super(InstaMedia.class);
        mTag = tag;
        mMaxId = maxId;
    }

    @Override
    public InstaMedia loadData() throws Exception {
        InstaBeanContainer beanContainer = InstaBeanContainer.getInstance();
        InstagramRestService restService = beanContainer.getInstagramRestService();
        Call<InstaFirstResult> firstResult = restService.getTagNodes(mTag, mMaxId);
        Response<InstaFirstResult> response = firstResult.execute();

        InstaFirstResult instaResult = response.body();
        return instaResult.getTag().getMedia();
    }

}
