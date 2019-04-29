package com.example.chatappfirebase.contactlist.ui;

import com.example.chatappfirebase.contactlist.entities.User;

public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
