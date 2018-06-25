package ru.badr.base.business.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abadretdinov
 * on 25.06.2018
 */
public abstract class Mapper<FROM, TO> {

    @Nullable
    public abstract TO map(@Nullable FROM value);

    @NonNull
    public List<TO> map(@Nullable List<FROM> values) {
        if (values == null) {
            return new ArrayList<>();
        }
        List<TO> returnValues = new ArrayList<>(values.size());
        for (FROM value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }
}
