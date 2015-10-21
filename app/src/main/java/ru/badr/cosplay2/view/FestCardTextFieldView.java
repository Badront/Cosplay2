package ru.badr.cosplay2.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.badr.cosplay2.R;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 14:32
 */
public class FestCardTextFieldView extends LinearLayout {
    protected ReqValuesHolder<String> mReqValueHolder;

    private TextView mTitleView;
    private TextView mValueView;

    public FestCardTextFieldView(Context context) {
        this(context, null);
    }

    public FestCardTextFieldView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FestCardTextFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FestCardTextFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    protected int getLayoutId() {
        return R.layout.fest_card_text_field;
    }

    public void setReqValueHolder(ReqValuesHolder<String> reqValueHolder) {
        this.mReqValueHolder = reqValueHolder;
        setTitle(mReqValueHolder.getTitle());
        if (mReqValueHolder.getValue() != null) {
            mValueView.setText(mReqValueHolder.getValue());
        }
    }

    @SuppressWarnings("deprecation")
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(context, getLayoutId(), this);

        mTitleView = (TextView) findViewById(android.R.id.text1);
        mValueView = (TextView) findViewById(android.R.id.text2);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FestCardTextFieldView, defStyleAttr, defStyleRes);
            CharSequence title = typedArray.getText(R.styleable.FestCardTextFieldView_android_text);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
            int ap = typedArray.getResourceId(R.styleable.FestCardTextFieldView_android_textAppearance, 0);
            if (ap != 0) {
                mTitleView.setTextAppearance(context, ap);
            }
            ColorStateList colors = typedArray.getColorStateList(R.styleable.FestCardTextFieldView_android_textColor);
            if (colors != null) {
                mTitleView.setTextColor(colors);
            }
            ap = typedArray.getResourceId(R.styleable.FestCardTextFieldView_fctf_text2Appearance, 0);
            if (ap != 0) {
                mValueView.setTextAppearance(context, ap);
            }
            colors = typedArray.getColorStateList(R.styleable.FestCardTextFieldView_fctf_text2Color);
            if (colors != null) {
                mValueView.setTextColor(colors);
            }
            typedArray.recycle();
        }
    }

    public void setTitle(CharSequence title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }
}
