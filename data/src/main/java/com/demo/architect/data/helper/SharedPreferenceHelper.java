package com.demo.architect.data.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by uyminhduc on 4/5/17.
 */

public class SharedPreferenceHelper {
    private static final String PREFERENCE_MAIN = "com.demo.music.town.MAIN";
    private static final String ADMIN_TOKEN = "admin_token";
    private SharedPreferences sharedPreferences;

    private static SharedPreferenceHelper _instance;

    public static SharedPreferenceHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new SharedPreferenceHelper(context);
        }
        return _instance;
    }

    public SharedPreferenceHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_MAIN, Context.MODE_PRIVATE);
    }

    public void pushString(String key, String val) {
        sharedPreferences.edit().putString(key, val).apply();
    }

    public String getString(String key, String def) {
        return sharedPreferences.getString(key, def);
    }

    public void pushBoolean(String key, boolean bool) {
        sharedPreferences.edit().putBoolean(key, bool).apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }


    public boolean existKey(String key) {
        return sharedPreferences.contains(key);
    }

    public void pushToken(String token) {
        pushString(ADMIN_TOKEN, token);
    }

    public String getToken() {
        String token = getString(ADMIN_TOKEN, null);
        return token;
    }
}
