package com.example.chatappfirebase.contactlist.events;

import com.example.chatappfirebase.contactlist.entities.User;

public class ContactListEvent {

    public User user;
    private int eventType;

    public final static int ON_CONTACT_ADDED =0;
    public final static int ON_CONTACT_CHANGED=1;
    public final static int ON_CONTACT_REMOVED=2;

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;

    }
    public int getEventType(){
        return eventType;
    }
    public void setEventType(int eventType){
        this.eventType = eventType;
    }
}
