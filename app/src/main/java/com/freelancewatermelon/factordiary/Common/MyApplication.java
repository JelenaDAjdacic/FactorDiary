package com.freelancewatermelon.factordiary.Common;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 1 on 3/8/2016.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;
    private MyPreferenceManager pref;
    private SessionManager sessionManager;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true); // enable offline capabilities
    }

    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }

    public SessionManager getSessionManager() {
        if (sessionManager == null) {
            sessionManager = new SessionManager(this);
        }
        return sessionManager;
    }
}
