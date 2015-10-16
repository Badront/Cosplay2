package ru.badr.cosplay2.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 18:06
 */
public class JurySectionEntity implements Serializable {
    private String name;
    private java.util.List<JuryEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<JuryEntity> getList() {
        return list;
    }

    public void setList(java.util.List<JuryEntity> list) {
        this.list = list;
    }

    public static class List extends ArrayList<JurySectionEntity> {
        public List(Collection<? extends JurySectionEntity> collection) {
            super(collection);
        }

        public List(int capacity) {
            super(capacity);
        }

        public List() {
            super();
        }
    }
}
