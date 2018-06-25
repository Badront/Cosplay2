package ru.badr.base.business.validation;

import android.support.annotation.Nullable;

/**
 * Created by abadretdinov
 * on 08.02.2018
 */

public class WarningModel {
    @Nullable
    private final String tag;
    private final String message;

    public WarningModel(@Nullable String tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    @Nullable
    public String getTag() {
        return tag;
    }

    public String getMessage() {
        return message;
    }
}
