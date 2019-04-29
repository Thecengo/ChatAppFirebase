package com.example.chatappfirebase.login.events;

public class LoginEvent {

    public final static int ON_SIGN_ERROR=0;
    public final static int ON_SIGNUP_ERROR=1;
    public final static int ON_SIGNIN_SUCCESS=2;
    public final static int ON_SIGNUP_SUCCESS=3;
    public final static int ON_FAILED_TO_RECOVER_SESSION=4;

    private int eventType;
    private String errorMessage;


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
