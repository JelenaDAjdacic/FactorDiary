package com.freelancewatermelon.factordiary.Interface;

/**
 * Created by 1 on 9/5/2016.
 */
public interface SignInCallbackInterface {
    void onSignInWGoogleClick();

    void onCreateAccClick(String name, String last_name, String email, String pass);

    void onLogInClick(String email, String pass);

    void onChangePassClick(String email);
}
