package ru.badr.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.badr.base.R;

/**
 * Created by ABadretdinov
 * 25.06.2015
 * 15:33
 */
public class ConstrainedLinearLayout extends LinearLayout {
    private static final int MAX_VALUE = Integer.MAX_VALUE / 2 + 1;
    private int maxHeight = Integer.MAX_VALUE;
    private int maxWidth = Integer.MAX_VALUE;

    public ConstrainedLinearLayout(Context context) {
        super(context);
    }

    public ConstrainedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyAttrs(attrs, 0, 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ConstrainedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ConstrainedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void applyAttrs(AttributeSet paramAttributeSet, int defStyleAttr, int defStyleRes) {
        if (paramAttributeSet != null) {
            TypedArray localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstrainedLinearLayout, defStyleAttr, defStyleRes);
            this.maxWidth = localTypedArray.getDimensionPixelSize(0, Integer.MAX_VALUE);
            this.maxHeight = localTypedArray.getDimensionPixelSize(1, Integer.MAX_VALUE);
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if ((MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE) || (MeasureSpec.getMode(widthMeasureSpec) == MAX_VALUE))
            widthMeasureSpec = Math.min(MeasureSpec.getSize(widthMeasureSpec), this.maxWidth) | MeasureSpec.getMode(widthMeasureSpec);
        if ((MeasureSpec.getMode(heightMeasureSpec) == Integer.MIN_VALUE) || (MeasureSpec.getMode(heightMeasureSpec) == MAX_VALUE))
            heightMeasureSpec = Math.min(MeasureSpec.getSize(heightMeasureSpec), this.maxHeight) | MeasureSpec.getMode(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
