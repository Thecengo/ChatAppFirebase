package com.example.chatappfirebase.login.ui;

public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void newUserSuccess();
    void newUserError(String error);
}
