package com.dapurtempoedoeloe.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dapurtempoedoeloe.model.Meja;
import com.google.gson.Gson;

public class PrefUtil {

    public static final String USER_SESSION = "user_session";

    public static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void putMeja(Context context, String key, Meja meja) {
        Gson gson = new Gson();
        String json = gson.toJson(meja);
        putString(context, key, json);
    }
    public static Meja getMeja(Context context, String key) {
        Gson gson = new Gson();
        String json = getString(context, key);
        Meja meja = gson.fromJson(json, Meja.class);
        return meja;
    }
    public static void putString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }
    public static String getString(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }
    public static void clear(Context context) {
        getSharedPreference(context).edit().clear().apply();
    }
}
