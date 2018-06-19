package ru.badr.base.util;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ABadretdinov
 * 23.09.2015
 * 13:39
 */
public class MapUtils {
    public static String getStaticMapUrl(Context context, String location, long width, long height, boolean needMarker) {
        StringBuilder url = new StringBuilder("http://maps.googleapis.com/maps/api/staticmap?zoom=15&maptype=roadmap&sensor=false&language=RU");
        try {
            if (needMarker) {
                String encodedDivider = URLEncoder.encode("|", "UTF-8");
                url.append("&markers=color:red").append(encodedDivider);
            } else {
                url.append("&center=");
            }
            url.append(URLEncoder.encode(location, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url.append("&size=").append(width).append("x").append(height);
        if (context.getResources().getDisplayMetrics().density > 1.5f) {
            url.append("&scale=2");
        }
        return url.toString();
    }

}
