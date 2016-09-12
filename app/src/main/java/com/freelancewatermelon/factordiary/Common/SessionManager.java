package com.freelancewatermelon.factordiary.Common;

import android.content.Context;

public class SessionManager {

    // Shared Preferences
    MyPreferenceManager pref;
    Context _context;

    public SessionManager(Context context) {
        this._context = context;
        pref = MyApplication.getInstance().getPrefManager();
    }

}