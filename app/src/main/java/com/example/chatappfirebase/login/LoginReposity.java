package com.example.chatappfirebase.login;

public interface LoginReposity {
    void signUp(final String email,final String password);
    void signIn(String email,String password);
    void checkAlreadyAuthenticated();
}
