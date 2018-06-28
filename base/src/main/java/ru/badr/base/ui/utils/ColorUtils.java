package ru.badr.base.ui.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import ru.badr.base.R;

/**
 * Created by abadretdinov
 * on 28.06.2018
 */
public final class ColorUtils {
    @ColorInt
    public static int getAccentColor(@NonNull Context context) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        int color = a.getColor(0, 0);

        a.recycle();
        return color;
    }
}
