package ru.badr.cosplay2.api.cards.info.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 21.10.2015
 * 18:46
 */
public class ReqSection<T> implements Serializable {
    private List<ReqValuesHolder<T>> list;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ReqValuesHolder<T>> getList() {
        return list;
    }

    public void setList(List<ReqValuesHolder<T>> list) {
        this.list = list;
    }
}
