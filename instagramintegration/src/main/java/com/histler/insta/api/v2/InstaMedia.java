package com.histler.insta.api.v2;

import com.google.gson.annotations.SerializedName;
import com.histler.insta.api.v2.node.InstaNode;

import java.util.List;

/**
 * Created by Badr
 * on 02.09.2016 1:38.
 */
public class InstaMedia extends InstaCounter {
    @SerializedName("page_info")
    private InstaPageInfo pageInfo;
    private List<InstaNode> nodes;

    public InstaPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(InstaPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<InstaNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<InstaNode> nodes) {
        this.nodes = nodes;
    }
}
