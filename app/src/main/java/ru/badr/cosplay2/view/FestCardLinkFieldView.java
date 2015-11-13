package ru.badr.cosplay2.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 16:51
 */
public class FestCardLinkFieldView extends FestCardTextFieldView implements View.OnClickListener {
    public FestCardLinkFieldView(Context context) {
        super(context);
    }

    public FestCardLinkFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FestCardLinkFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FestCardLinkFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fest_card_link_field;
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.init(context, attrs, defStyleAttr, defStyleRes);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mReqValueHolder != null && !TextUtils.isEmpty(mReqValueHolder.getValue())) {
            Uri uri = Uri.parse(mReqValueHolder.getValue());
            getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri));
            mReqValueHolder.getValue();
        }
    }
}
