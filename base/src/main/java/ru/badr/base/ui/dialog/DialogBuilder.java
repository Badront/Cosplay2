package ru.badr.base.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public class DialogBuilder extends MaterialDialog.Builder {

    protected DialogBuilder(@NonNull Context context) {
        super(context);
    }
}
