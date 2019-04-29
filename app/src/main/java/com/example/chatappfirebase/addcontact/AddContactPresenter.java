package com.example.chatappfirebase.addcontact;

import com.example.chatappfirebase.addcontact.event.AddContactEvent;

public interface AddContactPresenter {

    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
