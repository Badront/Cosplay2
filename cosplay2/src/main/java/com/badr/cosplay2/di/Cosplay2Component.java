package com.badr.cosplay2.di;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import ru.badr.base.di.BaseModule;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
@Component(modules = {Cosplay2Module.class, Cosplay2ViewModule.class, BaseModule.class})
public interface Cosplay2Component {
    void inject(Application application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Cosplay2Component build();
    }
}
