package ru.badr.cosplay2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;
import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 17:51
 */
public class FestCardUserFieldView extends FestCardView<User> {

    private TextView mTitleView;
    private TextView mUserNameView;
    private ImageView mAvatarView;

    public FestCardUserFieldView(Context context) {
        super(context);
    }

    public FestCardUserFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FestCardUserFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FestCardUserFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fest_card_user_field;
    }

    @SuppressWarnings("deprecation")
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.init(context, attrs, defStyleAttr, defStyleRes);

        mTitleView = (TextView) findViewById(android.R.id.text1);
        mUserNameView = (TextView) findViewById(android.R.id.text2);
        mAvatarView = (ImageView) findViewById(R.id.avatar);
    }

    public void setReqValueHolder(ReqValuesHolder<User> reqValueHolder) {
        super.setReqValueHolder(reqValueHolder);
        setTitle(mReqValueHolder.getTitle());
        if (mReqValueHolder.getValue() != null) {
            User user = mReqValueHolder.getValue();
            mUserNameView.setText(user.getTitle());
            Context context = getContext();
            Glide.with(context).load(Utils.getUserAvatar(context, user)).into(mAvatarView);
        }
    }

    public boolean isEmpty() {
        return mReqValueHolder.getValue() == null || mReqValueHolder.getValue().getId() == 0;
    }

    public void setTitle(CharSequence title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }
}
