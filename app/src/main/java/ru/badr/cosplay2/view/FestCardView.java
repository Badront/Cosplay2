package ru.badr.cosplay2.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;

/**
 * Created by Badr on 14.11.2015.
 */
public abstract class FestCardView<T> extends LinearLayout {
    protected ReqValuesHolder<T> mReqValueHolder;

    public FestCardView(Context context) {
        this(context, null);
    }

    public FestCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FestCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FestCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    protected abstract int getLayoutId();

    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(context, getLayoutId(), this);
    }

    public abstract boolean isEmpty();

    public void setReqValueHolder(ReqValuesHolder<T> reqValueHolder) {
        this.mReqValueHolder = reqValueHolder;
    }
}
