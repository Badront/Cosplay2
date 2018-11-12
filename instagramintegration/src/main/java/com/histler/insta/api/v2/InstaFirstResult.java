package com.histler.insta.api.v2;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:59.
 */
public class InstaFirstResult implements Serializable {
    private InstaGraphQl graphql;

    public InstaTag getTag() {
        return graphql.getHashtag();
    }

    public InstaGraphQl getGraphql() {
        return graphql;
    }

    public void setGraphql(InstaGraphQl graphql) {
        this.graphql = graphql;
    }
}
