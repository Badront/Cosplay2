package ru.badr.base.fragment;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 30.06.2015
 * 16:52
 */
public abstract class EntityLoader<T> implements Serializable {
    public boolean supportsPreLoading() {
        return true;
    }

    public abstract void loadEntities(Context context);

    public abstract List<T> getEntities(Context context, String... params);
}