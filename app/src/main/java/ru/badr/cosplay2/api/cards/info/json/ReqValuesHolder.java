package ru.badr.cosplay2.api.cards.info.json;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 18:05
 */
public class ReqValuesHolder<T> implements Serializable {
    private String title;
    private Types type;
    private T value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public enum Types {
        text,
        user,
        link,
        image
    }
}
