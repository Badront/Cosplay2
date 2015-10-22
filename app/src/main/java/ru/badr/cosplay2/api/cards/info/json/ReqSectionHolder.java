package ru.badr.cosplay2.api.cards.info.json;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 18:46
 */
public class ReqSectionHolder<T> implements Serializable {
    private java.util.List<ReqValuesHolder<T>> list = new ArrayList<>();
    private String title;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.List<ReqValuesHolder<T>> getList() {
        return list;
    }

    public void setList(java.util.List<ReqValuesHolder<T>> list) {
        this.list = list;
    }

    public static class List extends ArrayList<ReqSectionHolder> {

    }
}
