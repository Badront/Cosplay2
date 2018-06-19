package ru.badr.base.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 15:47
 */
public class SettingsUtils {

    public static final String SETTINGS_FILE = "settings.properties";

    public static String getAssetsSettingsProperty(Context context, String propertyName) {
        return getAssetsProperty(context, SETTINGS_FILE, propertyName);
    }

    public static String getAssetsProperty(Context context, String propertyFileName, String propertyName) {
        Properties properties = getAssetsProperties(context, propertyFileName);
        return properties != null ? properties.getProperty(propertyName) : null;
    }

    public static String getProperty(InputStream is, String propertyName) {
        Properties properties = getProperties(is);
        return properties != null ? properties.getProperty(propertyName) : null;
    }

    public static Properties getAssetsProperties(Context context) {
        return getAssetsProperties(context, SETTINGS_FILE);
    }

    public static Properties getAssetsProperties(Context context, String propertyFileName) {
        try {
            AssetManager assets = context.getAssets();
            InputStream inputStream = assets.open(propertyFileName);
            return getProperties(inputStream);
        } catch (IOException e) {
            Log.e(SettingsUtils.class.getName() + "getAssetsProperties", e.getMessage());
            return null;
        }
    }

    public static Properties getProperties(InputStream is) {
        try {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (IOException e) {
            Log.e(SettingsUtils.class.getName() + "getProperty", e.getMessage());
            return null;
        }
    }
}
