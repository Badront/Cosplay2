package ru.badr.base.adapter;

import android.view.View;

/**
 * Created by ABadretdinov
 * 30.09.2015
 * 17:02
 */
public interface OnItemCheckListener {
    void onCheckChanged(View view, int position, boolean isChecked);
}
