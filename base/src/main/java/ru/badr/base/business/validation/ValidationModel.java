package ru.badr.base.business.validation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abadretdinov
 * on 13.03.2018
 */

public class ValidationModel {
    private final boolean success;
    private List<WarningModel> warnings;
    private List<WarningModel> fails;

    private ValidationModel(boolean success, List<WarningModel> warnings, List<WarningModel> fails) {
        this.success = success;
        this.warnings = warnings;
        this.fails = fails;
    }

    public static ValidationModel success() {
        return new ValidationModel(true, null, null);
    }

    public static ValidationModel error(List<WarningModel> warnings, List<WarningModel> fails) {
        return new ValidationModel(false, warnings, fails);
    }

    public void addWarning(@NonNull String tag, @NonNull String errorText) {
        if (warnings == null) {
            warnings = new ArrayList<>();
        }
        warnings.add(new WarningModel(tag, errorText));
    }

    public boolean isSuccess() {
        return success;
    }

    @Nullable
    public List<WarningModel> getWarnings() {
        return warnings;
    }

    @Nullable
    public List<WarningModel> getFails() {
        return fails;
    }
}
