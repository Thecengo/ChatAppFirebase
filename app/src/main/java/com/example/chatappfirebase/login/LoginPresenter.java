package com.example.chatappfirebase.login;

import com.example.chatappfirebase.login.events.LoginEvent;

public interface LoginPresenter {

    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email,String password);
    void registerNewUser(String email,String password);
}
