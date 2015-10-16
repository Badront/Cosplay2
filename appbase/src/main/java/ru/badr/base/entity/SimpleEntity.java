package ru.badr.base.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 26.06.2015
 * 13:57
 */
public class SimpleEntity implements Serializable, HasId {
    @Expose
    private long id;
    @Expose
    private String name;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
