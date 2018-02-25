package com.apps.management.project.deny.projectmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Yedi on 11/26/2017.
 */

public class SessionManager {
    //logcat tag
    private static String TAG = SessionManager.class.getSimpleName();

    //shared preference
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    //shared pref mode
    int PRIVATE_MODE = 0;

    //shared preferences file name
    private static final String PREF_NAME = "TestingAndroid";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        //commit changes
        editor.commit();

        Log.d(TAG, "User Login Session Modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
