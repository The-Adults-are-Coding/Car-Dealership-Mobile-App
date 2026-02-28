package com.alissar.cardealershipapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private SharedPreferences prefs;
    private static final String PREF_NAME = "user_session";
    private static final String KEY_TOKEN = "jwt_token";
    private static final String ID = "id";

    @Inject
    public SessionManager(@ApplicationContext Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public void saveId(String id){
        prefs.edit().putString(ID,id).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public String getId(){


        return prefs.getString(ID,null);
    }

    public void clearSession() {
        prefs.edit().clear().apply();
    }
}