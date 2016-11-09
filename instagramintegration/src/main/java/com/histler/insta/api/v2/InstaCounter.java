package com.histler.insta.api.v2;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:51.
 */
public class InstaCounter implements Serializable {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
