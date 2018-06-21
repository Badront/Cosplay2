package ru.badr.cosplay2.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.badr.cosplay2.model.cards.info.json.ReqValuesHolder;

import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 14:32
 */
public class FestCardTextFieldView extends FestCardView<String> {

    private TextView mTitleView;
    private TextView mValueView;

    public FestCardTextFieldView(Context context) {
        super(context);
    }

    public FestCardTextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FestCardTextFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FestCardTextFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected int getLayoutId() {
        return R.layout.fest_card_text_field;
    }

    public void setReqValueHolder(ReqValuesHolder<String> reqValueHolder) {
        super.setReqValueHolder(reqValueHolder);
        setTitle(mReqValueHolder.getTitle());
        if (mReqValueHolder.getValue() != null) {
            mValueView.setText(mReqValueHolder.getValue());
        }
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(mReqValueHolder.getValue());
    }

    @SuppressWarnings("deprecation")
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.init(context, attrs, defStyleAttr, defStyleRes);

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
