package de.android.meetat.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by raina on 12.03.2016.
 */
public class SessionReminder {
    private static final String  MY_PREFS_NAME = "de.meetat";
    private static SessionReminder sessionReminder = null;
    private String KEY = null;
    private String NICKNAME = null;


    private SessionReminder(){

    }
    public static SessionReminder getSessionReminder(){
        if(sessionReminder == null){
            sessionReminder = new SessionReminder();
        }
        return sessionReminder;
    }

    public void saveLogin(String key, String nickname, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY, key);
        editor.putString(NICKNAME, nickname);
        editor.commit();
    }

    public boolean isLoged(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(KEY,null);
        return result != null;
    }
}
