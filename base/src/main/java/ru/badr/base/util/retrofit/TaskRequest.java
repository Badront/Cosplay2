package ru.badr.base.util.retrofit;

import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ABadretdinov
 * 26.06.2015
 * 13:10
 */
public abstract class TaskRequest<T> extends SpiceRequest<T> {
    public TaskRequest(Class<T> clazz) {
        super(clazz);
    }

    public abstract T loadData() throws Exception;

    @Override
    public T loadDataFromNetwork() throws Exception {
        return loadData();
    }
}
