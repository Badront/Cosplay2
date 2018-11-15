package com.histler.insta.task;

import com.histler.insta.InstaBeanContainer;
import com.histler.insta.api.InstaUserResult;
import com.histler.insta.api.v2.node.InstaUser;
import com.histler.insta.remote.InstagramUserRestApi;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 10.09.2016 1:09.
 */
public class InstaUserRequest extends TaskRequest<InstaUser> {
    private String mUserId;

    public InstaUserRequest(String userId) {
        super(InstaUser.class);
        mUserId = userId;
    }

    @Override
    public InstaUser loadData() throws Exception {
        InstaBeanContainer beanContainer = InstaBeanContainer.getInstance();
        InstagramUserRestApi restService = beanContainer.getInstagramUserRestApi();
        Call<InstaUserResult> firstResult = restService.getUser(mUserId);
        Response<InstaUserResult> response = firstResult.execute();
        InstaUserResult result = response.body();
        if (result == null) {
            return null;
        }
        return cast(result.getUser());
    }

    @Nullable
    private InstaUser cast(@Nullable com.histler.insta.api.InstaUser user) {
        if (user == null) {
            return null;
        }
        return new InstaUser(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getProfileImage()
        );
    }

    private String generateComplexUserId(String userId) {
        return "ig_user(" + userId + "){id,username,profile_pic_url}";
    }
}
