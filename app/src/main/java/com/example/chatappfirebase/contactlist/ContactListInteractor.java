package com.example.chatappfirebase.contactlist;

public interface ContactListInteractor {

    void subscribeForContactEvents();
    void unSubscribeForContactEvents();
    void destroyContactListListener();
    void removeContact(String email);
}
