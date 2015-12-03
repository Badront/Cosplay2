package ru.badr.cosplay2.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import ru.badr.cosplay2.api.cards.info.InfoCard;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;
import ru.badr.opencon.R;

/**
 * Created by Badr on 04.12.2015.
 */
public class FestCardWinFieldView extends FestCardView<InfoCard> {

    private TextView mTitleView;
    private TextView mWinTitleView;
    private AppCompatImageView mImageView;

    public FestCardWinFieldView(Context context) {
        super(context);
    }

    public FestCardWinFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FestCardWinFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FestCardWinFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fest_card_win_field;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.init(context, attrs, defStyleAttr, defStyleRes);

        mTitleView = (TextView) findViewById(android.R.id.text1);
        mImageView = (AppCompatImageView) findViewById(R.id.win);
        mWinTitleView = (TextView) findViewById(android.R.id.text2);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FestCardWinFieldView, defStyleAttr, defStyleRes);
            CharSequence title = typedArray.getText(R.styleable.FestCardWinFieldView_android_text);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
            int ap = typedArray.getResourceId(R.styleable.FestCardWinFieldView_android_textAppearance, 0);
            if (ap != 0) {
                mTitleView.setTextAppearance(context, ap);
            }
            ColorStateList colors = typedArray.getColorStateList(R.styleable.FestCardWinFieldView_android_textColor);
            if (colors != null) {
                mTitleView.setTextColor(colors);
            }
            typedArray.recycle();
        }
    }

    public void setReqValueHolder(ReqValuesHolder<InfoCard> reqValueHolder) {
        super.setReqValueHolder(reqValueHolder);
        if (mReqValueHolder.getValue() != null) {
            Context context = getContext();
            InfoCard card = mReqValueHolder.getValue();
            mWinTitleView.setText(card.getWinTitle());
            int color;
            if ("1".equals(card.getWin())) {
                color = R.color.gold;
            } else if ("2".equals(card.getWin())) {
                color = R.color.silver;
            } else {
                color = R.color.bronze;
            }
            mImageView.setColorFilter(context.getResources().getColor(color));
        }
    }

    @Override
    public boolean isEmpty() {
        return mReqValueHolder.getValue() == null || mReqValueHolder.getValue().getId() == 0;
    }

    public void setTitle(CharSequence title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }
}
