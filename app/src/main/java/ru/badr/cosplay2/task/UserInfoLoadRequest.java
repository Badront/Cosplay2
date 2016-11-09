package ru.badr.cosplay2.task;

import android.content.Context;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;
import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.info.json.GetUserResult;

/**
 * Created by Badr on 24.11.2015.
 */
public class UserInfoLoadRequest extends TaskRequest<User> {
    private Context mContext;
    private long mUserId;

    public UserInfoLoadRequest(Context context, long userId) {
        super(User.class);
        this.mContext = context.getApplicationContext();
        this.mUserId = userId;
    }

    @Override
    public User loadData() throws Exception {
        Cosplay2BeanContainer container = Cosplay2BeanContainer.getInstance(mContext);
        GetUserResult result = container.getCosplay2RestService().getUser(mUserId).execute().body();
        if (result != null) {
            return result.getUser();
        }
        return null;
    }
}
