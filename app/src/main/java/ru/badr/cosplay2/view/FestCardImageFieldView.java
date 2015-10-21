package ru.badr.cosplay2.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.badr.cosplay2.R;
import ru.badr.cosplay2.api.cards.info.InfoCard;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;
import ru.badr.cosplay2.util.Utils;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 13:51
 */
public class FestCardImageFieldView extends LinearLayout {
    private ReqValuesHolder<InfoCard> mReqValueHolder;

    private TextView mTitleView;
    private ImageView mImageView;

    public FestCardImageFieldView(Context context) {
        this(context, null);
    }

    public FestCardImageFieldView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FestCardImageFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FestCardImageFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(context, R.layout.fest_card_image_field, this);

        mTitleView = (TextView) findViewById(android.R.id.text1);
        mImageView = (ImageView) findViewById(R.id.image);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FestCardImageFieldView, defStyleAttr, defStyleRes);
            CharSequence title = typedArray.getText(R.styleable.FestCardImageFieldView_android_text);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
            int ap = typedArray.getResourceId(R.styleable.FestCardImageFieldView_android_textAppearance, 0);
            if (ap != 0) {
                mTitleView.setTextAppearance(context, ap);
            }
            ColorStateList colors = typedArray.getColorStateList(R.styleable.FestCardImageFieldView_android_textColor);
            if (colors != null) {
                mTitleView.setTextColor(colors);
            }
            typedArray.recycle();
        }
    }

    public void setReqValueHolder(ReqValuesHolder<InfoCard> reqValueHolder) {
        this.mReqValueHolder = reqValueHolder;
        setTitle(mReqValueHolder.getTitle());
        if (mReqValueHolder.getValue() != null) {
            Context context = getContext();
            Glide.with(context).load(Utils.getCardImageUrl(context, mReqValueHolder.getValue())).into(mImageView);
        }
    }

    public void setTitle(CharSequence title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }
}