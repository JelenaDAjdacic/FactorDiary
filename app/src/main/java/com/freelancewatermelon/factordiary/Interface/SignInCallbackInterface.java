package com.freelancewatermelon.factordiary.Interface;

public interface SignInCallbackInterface {
    void onSignInWGoogleClick();

    void onCreateAccClick(String name, String last_name, String email, String pass);

    void onLogInClick(String email, String pass);

    void onForgotPassClick(String email);
}
