package com.example.chatappfirebase.contactlist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.chatappfirebase.contactlist.entities.User;
import com.example.chatappfirebase.contactlist.events.ContactListEvent;
import com.example.chatappfirebase.domain.FirebaseHelper;
import com.example.chatappfirebase.lib.EventBus;
import com.example.chatappfirebase.lib.GreenRobotEventBus;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class ContactListRepositoryImpl implements ContactListRepository {
    private FirebaseHelper firebaseHelper;
    private ChildEventListener contactListEventListener;

    public ContactListRepositoryImpl(){
        firebaseHelper = FirebaseHelper.getInstance();
    }
    @Override
    public void signOff() {
        firebaseHelper.signOff();

    }

    @Override
    public String getCurrentEmail() {
        return firebaseHelper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = firebaseHelper.getAuthUserEmail();
        firebaseHelper.getOneContactReference(currentUserEmail,email).removeValue();
        firebaseHelper.getOneContactReference(email,currentUserEmail).removeValue();

    }

    @Override
    public void destroyContactListListener() {
        contactListEventListener = null;

    }

    @Override
    public void subscribeForContactListUpdates() {
        if(contactListEventListener == null){
            contactListEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_",".");
                    boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
                    User user = new User(email,online,null);
                    postEvent(ContactListEvent.ON_CONTACT_ADDED,user);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_",".");
                    boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
                    User user = new User(email,online,null);
                    postEvent(ContactListEvent.ON_CONTACT_CHANGED,user);

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_",".");
                    boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
                    User user = new User(email,online,null);
                    postEvent(ContactListEvent.ON_CONTACT_REMOVED,user);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
        }
        firebaseHelper.getMyContactsReference().addChildEventListener(contactListEventListener);

    }

    @Override
    public void unSubscribeForContactListUpdates() {
        if(contactListEventListener != null){
            firebaseHelper.getMyContactsReference().removeEventListener(contactListEventListener);
        }

    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        firebaseHelper.changeUserConnectionStatus(online);

    }
    private void postEvent(int type,User user){
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEventType(type);
        contactListEvent.setUser(user);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(contactListEvent);
    }
}
