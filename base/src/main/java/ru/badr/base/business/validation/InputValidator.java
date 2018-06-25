package ru.badr.base.business.validation;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abadretdinov
 * on 13.03.2018
 */

public abstract class InputValidator<T> {
    private final WeakReference<Context> contextRef;

    public InputValidator(Context context) {
        this.contextRef = new WeakReference<>(context);
    }

    @Nullable
    protected Context getContext() {
        return contextRef.get();
    }

    protected abstract void putErrors(@NonNull Context context, @NonNull List<WarningModel> errors, @NonNull T value);

    @CallSuper
    protected void putFails(@NonNull Context context, @NonNull List<WarningModel> fails, @NonNull T value) {
    }

    public ValidationModel validate(@NonNull T value) {
        List<WarningModel> errors = new ArrayList<>();

        Context context = getContext();
        if (context != null) {
            putErrors(context, errors, value);
        }

        List<WarningModel> fails = new ArrayList<>();
        if (context != null) {
            putFails(context, fails, value);
        }

        if (errors.isEmpty() && fails.isEmpty()) {
            return ValidationModel.success();
        }

        return ValidationModel.error(errors, fails);
    }
}