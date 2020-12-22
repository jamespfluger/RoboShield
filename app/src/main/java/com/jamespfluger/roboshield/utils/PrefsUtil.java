package com.jamespfluger.roboshield.utils;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

public class PrefsUtil {
    public static <T> void putList(String key, List<T> list) {
        String listJson = JsonConverter.fromList(list);
        Prefs.putString(key, listJson);
    }

    public static <T> List<T> getList(String key) {
        String listJson = Prefs.getString(key, null);
        return JsonConverter.toList(listJson);
    }

    public static void putString(String key, String value) {
        Prefs.putString(key, value);
    }

    public static String getString(String key) {
        return Prefs.getString(key, null);
    }

    public static void putBoolean(String key, boolean value) {
        Prefs.putBoolean(key, value);
    }

    public static boolean getBoolean(String key) {
        return Prefs.getBoolean(key, false);
    }
}
