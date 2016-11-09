package com.histler.insta.task;

import com.histler.insta.InstaBeanContainer;
import com.histler.insta.api.v2.node.InstaUser;
import com.histler.insta.remote.InstagramRestService;

import retrofit2.Call;
import retrofit2.Response;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 10.09.2016 1:09.
 */
public class InstaUserRequest extends TaskRequest<InstaUser> {
    String mUserId;

    public InstaUserRequest(String userId) {
        super(InstaUser.class);
        mUserId = userId;
    }

    @Override
    public InstaUser loadData() throws Exception {
        InstaBeanContainer beanContainer = InstaBeanContainer.getInstance();
        InstagramRestService restService = beanContainer.getInstagramRestService();
        Call<InstaUser> firstResult = restService.getUserInfo(generateComplexUserId(mUserId));
        Response<InstaUser> response = firstResult.execute();

        return response.body();
    }

    private String generateComplexUserId(String userId) {
        return "ig_user(" + userId + "){id,username,profile_pic_url}";
    }
}
