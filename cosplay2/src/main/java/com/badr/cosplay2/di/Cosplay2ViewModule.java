package com.badr.cosplay2.di;

import com.badr.cosplay2.ui.fragment.events.EventsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by abadretdinov
 * on 29.06.2018
 */
@Module(includes = Cosplay2Module.class)
public interface Cosplay2ViewModule {
    @ContributesAndroidInjector
    EventsFragment eventsFragment();

}
