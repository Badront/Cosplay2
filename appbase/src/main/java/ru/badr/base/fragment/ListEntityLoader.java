package ru.badr.base.fragment;

import android.content.Context;

import java.util.List;

/**
 * Created by ABadretdinov
 * 20.07.2015
 * 17:07
 */
public class ListEntityLoader<T> extends EntityLoader<T> {
    private List<T> mEntities;

    public ListEntityLoader(List<T> entities) {
        this.mEntities = entities;
    }

    @Override
    public void loadEntities(Context context) {

    }

    @Override
    public List<T> getEntities(Context context, String... params) {
        return mEntities;
    }
}
