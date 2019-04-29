package com.example.chatappfirebase.login.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.chatappfirebase.R;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.btnSignin) Button btnSignIn;
    @BindView(R.id.btnSignup) Button btnSignUp;
    @BindView(R.id.editTxtEmail) EditText editTextEmail;
    @BindView(R.id.editTxtPassword) EditText editTextPassword;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer) CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void handleSignUp() {

    }

    @Override
    public void handleSignIn() {

    }

    @Override
    public void newUserSuccess() {

    }

    @Override
    public void newUserError(String error) {

    }
}
