package com.example.chatappfirebase.addcontact;

import android.support.annotation.NonNull;

import com.example.chatappfirebase.addcontact.event.AddContactEvent;
import com.example.chatappfirebase.contactlist.entities.User;
import com.example.chatappfirebase.domain.FirebaseHelper;
import com.example.chatappfirebase.lib.EventBus;
import com.example.chatappfirebase.lib.GreenRobotEventBus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AddContactRepositoryImpl implements AddContactRepository{

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");

        FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = firebaseHelper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                AddContactEvent event = new AddContactEvent();
                if(user != null){
                    boolean online = user.isOnline();
                    FirebaseHelper helper = FirebaseHelper.getInstance();
                    DatabaseReference userContactsReference = helper.getMyContactsReference();
                    userContactsReference.child(key).setValue(online);

                    String currentUserEmailKey = helper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".","_");
                    DatabaseReference reverseUserContactsReference = helper.getContactReference(email);
                    reverseUserContactsReference.child(currentUserEmailKey).setValue(true);
                }
                else {
                    event.setError(true);
                }
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(event);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
