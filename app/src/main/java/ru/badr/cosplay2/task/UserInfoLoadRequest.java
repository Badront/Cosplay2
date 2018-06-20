package ru.badr.cosplay2.task;

import android.content.Context;

import com.badr.cosplay2.model.cards.User;
import com.badr.cosplay2.model.cards.info.json.GetUserResult;

import ru.badr.base.util.retrofit.TaskRequest;
import ru.badr.cosplay2.Cosplay2BeanContainer;

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
