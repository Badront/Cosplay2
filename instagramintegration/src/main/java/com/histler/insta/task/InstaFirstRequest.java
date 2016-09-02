package com.histler.insta.task;

import android.content.Context;
import android.support.annotation.NonNull;

import com.histler.insta.InstaBeanContainer;
import com.histler.insta.api.InstaResult;
import com.histler.insta.api.v2.InstaFirstResult;
import com.histler.insta.api.v2.InstaSecondResult;
import com.histler.insta.remote.InstagramRestService;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by Badr
 * on 02.09.2016 2:13.
 */
public class InstaFirstRequest extends TaskRequest<InstaResult> {
    private static final String TOKEN = "csrftoken";
    private static final String SESSION_ID = "sessionid";
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
        Headers headers = response.headers();
        List<String> cookies = headers.values("set-cookie");
        String token = null, sessionId = null;
        if (cookies != null) {
            for (String cookie : cookies) {
                if (cookie.contains(TOKEN)) {
                    token = getCookieValue(cookie, TOKEN, false);
                } else if (cookie.contains(SESSION_ID)) {
                    sessionId = getCookieValue(cookie, SESSION_ID, true);
                }
                if (token != null && sessionId != null) {
                    sessionId += "; " + TOKEN + "=" + token;
                    break;
                }
            }
        }
        InstaFirstResult instaResult = response.body();
        String next = instaResult.getTag().getMedia().getPageInfo().getNextPageTag();
        Call<InstaSecondResult> secondResult = restService.getTagNodes(generateQ(mTag, next, 20), "tags::show", generateRefererHeader(mTag), sessionId, token);

        Response<InstaSecondResult> response2 = secondResult.execute();
        Headers headers1 = response2.headers();
        InstaSecondResult secondResult1 = response2.body();
        return null;
    }

    @NonNull
    private String getCookieValue(String cookie, String valueName, boolean withName) {
        String temp;
        String tokenParam;
        temp = cookie.substring(cookie.indexOf(valueName) + (withName ? 0 : (valueName.length() + 1)));
        if (temp.contains(";")) {
            temp = temp.substring(0, temp.indexOf(";"));
        }
        tokenParam = temp;
        return tokenParam;
    }

}
