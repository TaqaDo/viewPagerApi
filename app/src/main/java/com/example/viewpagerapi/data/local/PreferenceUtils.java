package com.example.viewpagerapi.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private static SharedPreferences mPreference;
    private final static String APP_PREF_NAME = "key";
    private final static String USER_NAME = "kayname";

    public static void init(Context context) {
        mPreference = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE);
    }
    public static void saveUser(String userName) {
        mPreference.edit().putString(USER_NAME, userName).apply();
    }

    public static String getUser() {
        return mPreference.getString(USER_NAME, "");
    }

}
