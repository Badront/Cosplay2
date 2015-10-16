package ru.badr.cosplay2;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 15:51
 */
public class Cosplay2Application extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "H7W6RK5kUIndUoYQnWbuQxzRy";
    private static final String TWITTER_SECRET = "T2eXWyBXsEqkwVl3QeeYoSD47Yq2OFzEjcD5c2RGaai4qPlMNl";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        BeanInitializer.getInstance(this);
    }
}
