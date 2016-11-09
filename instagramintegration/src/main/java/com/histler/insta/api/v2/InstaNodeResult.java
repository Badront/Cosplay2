package com.histler.insta.api.v2;

import com.google.gson.annotations.SerializedName;
import com.histler.insta.api.v2.node.InstaNode;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 2:02.
 */
public class InstaNodeResult implements Serializable {
    @SerializedName("media")
    private InstaNode node;

    public InstaNode getNode() {
        return node;
    }

    public void setNode(InstaNode node) {
        this.node = node;
    }
}
