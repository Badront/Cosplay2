package ru.badr.base.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by abadretdinov
 * on 23.01.2018
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface Presenter {
}
