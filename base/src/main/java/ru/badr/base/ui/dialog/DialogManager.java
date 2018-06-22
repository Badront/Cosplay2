package ru.badr.base.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.inject.Inject;

import ru.badr.base.R;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public class DialogManager implements IDialogManager {
    private final Queue<String> messages;
    private boolean isShowing;
    private WeakReference<Context> contextRef;
    @Nullable
    private OnDialogFinishListener onDialogFinishListener;

    @Inject
    public DialogManager(@NonNull Context context) {
        this.contextRef = new WeakReference<>(context);
        messages = new ArrayDeque<>();
        isShowing = false;
    }

    @NonNull
    @Override
    public DialogBuilder create() {
        return new DialogBuilder(contextRef.get());
    }

    @Override
    public synchronized void showMessage(@NonNull String message, @Nullable OnDialogFinishListener onDialogFinishListener) {
        messages.add(message);
        this.onDialogFinishListener = onDialogFinishListener;

        if (!isShowing) {
            showNextDialog();
        }
    }

    private void showNextDialog() {
        if (messages.isEmpty()) {
            isShowing = false;
            if (onDialogFinishListener != null) {
                onDialogFinishListener.onFinish();
            }
        } else {
            isShowing = true;
            String message = messages.poll();
            create()
                    .content(message)
                    .positiveText(R.string.dialog_ok)
                    .onPositive(((dialog, which) -> {
                        dialog.dismiss();
                        showNextDialog();
                    }))
                    .show();
        }
    }
}
