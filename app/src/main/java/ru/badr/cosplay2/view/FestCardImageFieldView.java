package ru.badr.cosplay2.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.badr.cosplay2.model.cards.info.InfoCard;
import com.badr.cosplay2.model.cards.info.json.ReqValuesHolder;
import com.bumptech.glide.Glide;

import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 13:51
 */
public class FestCardImageFieldView extends FestCardView<InfoCard> {

    private TextView mTitleView;
    private ImageView mImageView;

    public FestCardImageFieldView(Context context) {
        super(context);
    }

    public FestCardImageFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FestCardImageFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FestCardImageFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fest_card_image_field;
    }

    @SuppressWarnings("deprecation")
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.init(context, attrs, defStyleAttr, defStyleRes);

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
        super.setReqValueHolder(reqValueHolder);
        setTitle(mReqValueHolder.getTitle());
        if (mReqValueHolder.getValue() != null) {
            Context context = getContext();
            Glide.with(context).load(Utils.getCardImageUrl(context, mReqValueHolder.getValue())).into(mImageView);
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
