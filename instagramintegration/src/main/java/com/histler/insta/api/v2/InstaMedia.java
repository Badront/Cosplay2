package com.histler.insta.api.v2;

import com.google.gson.annotations.SerializedName;
import com.histler.insta.api.v2.node.InstaNode;
import com.histler.insta.api.v2.node.InstaNodeEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Badr
 * on 02.09.2016 1:38.
 */
public class InstaMedia extends InstaCounter {
    @SerializedName("page_info")
    private InstaPageInfo pageInfo;
    private List<InstaNodeEdge> edges;

    public InstaPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(InstaPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<InstaNode> getNodes() {
        if (edges != null) {
            List<InstaNode> nodes = new ArrayList<>(edges.size());
            for (InstaNodeEdge nodeEdge : edges) {
                nodes.add(nodeEdge.getNode());
            }
            return nodes;
        }
        return null;
    }

    public List<InstaNodeEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<InstaNodeEdge> edges) {
        this.edges = edges;
    }
}
