package com.histler.insta.task;

import android.content.Context;

import com.histler.insta.InstaBeanContainer;
import com.histler.insta.api.InstaResult;
import com.histler.insta.api.v2.InstaFirstResult;
import com.histler.insta.api.v2.InstaSecondResult;
import com.histler.insta.remote.InstagramRestService;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 02.09.2016 2:13.
 */
public class InstaFirstRequest extends TaskRequest<InstaResult> {
    private Context mContext;
    private String mTag;

    public InstaFirstRequest(Context context, String tag) {
        super(InstaResult.class);
        mContext = context;
        mTag = tag;
    }

    private static String generateQ(String tag, String next, int count) {
        return String.format("ig_hashtag(%1$s) { media.after(%2$s, %3$d) {  count,  nodes {    caption,    code,    comments {      count    },    comments_disabled,    date,    dimensions {      height,      width    },    display_src,    id,    is_video,    likes {      count    },    owner {      id    },    thumbnail_src,    video_views  },  page_info} }", tag, next, count);
    }

    private static String generateRefererHeader(String tag) {
        return String.format("https://www.instagram.com/explore/tags/%1$s/", tag);
    }

    @Override
    public InstaResult loadData() throws Exception {
        InstaBeanContainer beanContainer = InstaBeanContainer.getInstance();
        InstagramRestService restService = beanContainer.getInstagramRestService();
        Call<InstaFirstResult> firstResult = restService.getFirstTagNodes(mTag);
        Response<InstaFirstResult> response = firstResult.execute();

        InstaFirstResult instaResult = response.body();
        String next = instaResult.getTag().getMedia().getPageInfo().getNextPageTag();
        Call<InstaSecondResult> secondResult = restService.getTagNodes(generateQ(mTag, next, 20), "tags::show", generateRefererHeader(mTag));

        Response<InstaSecondResult> response2 = secondResult.execute();
        Headers headers1 = response2.headers();
        InstaSecondResult secondResult1 = response2.body();
        return null;
    }

}
