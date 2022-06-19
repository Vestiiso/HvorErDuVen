package com.example.hvorerduven;

import android.content.Context;
import android.content.SharedPreferences;

//Indholdet i denne class er taget fra: https://stackoverflow.com/questions/24039577/android-sharedpreferences-editing-not-working
public class Pref {

    private static final String LOGGET_IND = "loggetInd";
    private static final String SHARED_PREFS = "sharedPrefs";

    private static Pref instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private Pref(Context context) {
        preferences = context.getSharedPreferences(SHARED_PREFS, 0);
        editor = preferences.edit();
    }

    public static Pref getInstance(Context context) {
        return instance == null ? new Pref(context) : instance;
    }

    /*public void setUserID(String userID) {
        editor.putString(LOGGET_IND, userID).commit();
    }

     */

    public void setLoggetInd(boolean loggetInd) {
        editor.putBoolean(LOGGET_IND, loggetInd).commit();
        System.out.println("logget ind pref er nu sat til: " + getLoggetInd());
    }

    /* public String getUserID() {
        return preferences.getString(LOGGET_IND, "");
    }

     */

    public String getLoggetInd() {
        return preferences.getString(LOGGET_IND, "");
    }
}

