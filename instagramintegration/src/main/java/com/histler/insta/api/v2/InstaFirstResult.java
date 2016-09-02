package com.histler.insta.api.v2;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:59.
 */
public class InstaFirstResult implements Serializable {
    private InstaTag tag;

    public InstaTag getTag() {
        return tag;
    }

    public void setTag(InstaTag tag) {
        this.tag = tag;
    }
}
