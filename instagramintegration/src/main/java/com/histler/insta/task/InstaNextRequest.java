package com.histler.insta.task;

import com.histler.insta.InstaBeanContainer;
import com.histler.insta.api.v2.InstaSecondResult;
import com.histler.insta.remote.InstagramRestService;

import retrofit2.Call;
import retrofit2.Response;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 10.09.2016 0:18.
 */
public class InstaNextRequest extends TaskRequest<InstaSecondResult> {
    private String mNextId;
    private String mTag;

    public InstaNextRequest(String tag, String nextId) {
        super(InstaSecondResult.class);
        mTag = tag;
        mNextId = nextId;
    }

    private static String generateQ(String tag, String next, int count) {
        return String.format("ig_hashtag(%1$s) { media.after(%2$s, %3$d) {  count,  nodes {    caption,    code,    comments {      count    },    date,    dimensions {      height,      width    },    display_src,    id,    is_video,    likes {      count    },    owner {id, profile_pic_url, username },thumbnail_src, video_views  },  page_info} }", tag, next, count);
    }

    private static String generateRefererHeader(String tag) {
        return String.format("https://www.instagram.com/explore/tags/%1$s/", tag);
    }

    @Override
    public InstaSecondResult loadData() throws Exception {
        InstaBeanContainer beanContainer = InstaBeanContainer.getInstance();
        InstagramRestService restService = beanContainer.getInstagramRestService();

        //String next = instaResult.getTag().getMedia().getPageInfo().getNextPageTag();
        Call<InstaSecondResult> call = restService.getTagNodes(generateQ(mTag, mNextId, 20), "tags::show", generateRefererHeader(mTag));

        Response<InstaSecondResult> response = call.execute();
        InstaSecondResult result = response.body();
        return result;
    }
}
