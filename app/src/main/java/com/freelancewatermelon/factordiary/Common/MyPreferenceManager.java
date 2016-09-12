package com.freelancewatermelon.factordiary.Common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 1 on 4/19/2016.
 */
public class MyPreferenceManager {

    // Sharedpref file name
    private static final String PREF_NAME = "heamophilia_prefs";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
}