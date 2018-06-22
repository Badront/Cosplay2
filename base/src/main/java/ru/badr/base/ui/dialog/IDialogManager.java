package ru.badr.base.ui.dialog;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public interface IDialogManager {
    @NonNull
    DialogBuilder create();

    @MainThread
    void showMessage(@NonNull String message, @Nullable OnDialogFinishListener onDialogFinishListener);

    interface OnDialogFinishListener {
        void onFinish();
    }
}
