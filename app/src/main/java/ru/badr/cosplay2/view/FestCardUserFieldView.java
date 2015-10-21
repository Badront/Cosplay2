package ru.badr.cosplay2.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.badr.cosplay2.R;
import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;
import ru.badr.cosplay2.util.Utils;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 17:51
 */
public class FestCardUserFieldView extends LinearLayout {
    private ReqValuesHolder<User> mReqValueHolder;

    private TextView mTitleView;
    private TextView mUserNameView;
    private ImageView mAvatarView;

    public FestCardUserFieldView(Context context) {
        this(context, null);
    }

    public FestCardUserFieldView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FestCardUserFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FestCardUserFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(context, R.layout.fest_card_user_field, this);

        mTitleView = (TextView) findViewById(android.R.id.text1);
        mUserNameView = (TextView) findViewById(android.R.id.text2);
        mAvatarView = (ImageView) findViewById(R.id.avatar);
    }

    public void setReqValueHolder(ReqValuesHolder<User> reqValueHolder) {
        this.mReqValueHolder = reqValueHolder;
        setTitle(mReqValueHolder.getTitle());
        if (mReqValueHolder.getValue() != null) {
            User user = mReqValueHolder.getValue();
            mUserNameView.setText(user.getTitle());
            Context context = getContext();
            Glide.with(context).load(Utils.getUserAvatar(context, user)).into(mAvatarView);
        }
    }

    public void setTitle(CharSequence title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }
}
