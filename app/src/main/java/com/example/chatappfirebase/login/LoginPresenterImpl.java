package com.example.chatappfirebase.login;

import com.example.chatappfirebase.lib.GreenRobotEventBus;
import com.example.chatappfirebase.login.events.LoginEvent;
import com.example.chatappfirebase.login.ui.LoginView;

import org.greenrobot.eventbus.EventBus;

public class LoginPresenterImpl implements LoginPresenter {
    GreenRobotEventBus eventBus;
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.eventBus = GreenRobotEventBus.getInstance();
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void checkForAuthenticatedUser() {

    }

    @Override
    public void onEventMainThread() {

    }

    @Override
    public void onEventMainThread(LoginEvent event) {

    }

    @Override
    public void validateLogin(String email, String password) {

    }

    @Override
    public void registerNewUser(String email, String password) {

    }
}
