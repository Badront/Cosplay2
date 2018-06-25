package ru.badr.base.ui.adapter;

/**
 * Created by abadretdinov
 * on 25.06.2018
 */
public interface AdapterComparable<T> {
    boolean areItemSame(Object item);

    boolean areContentSame(T item);

}
