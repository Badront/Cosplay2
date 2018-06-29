package ru.badr.base.business;

import ru.badr.base.SchedulersProvider;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
public abstract class BaseInteractor {
    protected final SchedulersProvider schedulersProvider;

    public BaseInteractor(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }
}
