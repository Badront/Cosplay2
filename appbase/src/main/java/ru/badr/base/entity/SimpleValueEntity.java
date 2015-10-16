package ru.badr.base.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by ABadretdinov
 * 26.06.2015
 * 14:31
 */
public class SimpleValueEntity extends SimpleEntity {
    @Expose
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
