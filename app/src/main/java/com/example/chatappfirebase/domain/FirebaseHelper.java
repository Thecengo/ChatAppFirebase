package com.example.chatappfirebase.domain;

import android.support.annotation.NonNull;

import com.example.chatappfirebase.contactlist.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    private DatabaseReference databaseReference;

    private final static String SEPERATOR ="___";

    private final static String CHATS_PATH ="chats";

    private final static String USERS_PATH="users";

    private final static String CONTACT_PATH ="contacts";

    private static class SingletonHolder {

        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabaseReference(){

        return databaseReference;
    }

    public String getAuthUserEmail(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String email = null;

        if(user != null)
            email = user.getEmail();
        return email;

    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;

        if(email != null){
            String emailKey = email.replace(".","_");
            userReference = databaseReference.getRoot().child(USERS_PATH).child(emailKey);

        }
        return userReference;
    }
    public DatabaseReference getMyUserReference(){
        return  getUserReference(getAuthUserEmail());
    }
    public DatabaseReference getContactReference(String email){
        return getUserReference(email).child(CONTACT_PATH);
    }

    public DatabaseReference getMyContactsReference(){
        return getContactReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String mainMail,String childEmail){
        String childKey = childEmail.replace(".","_");
        return getUserReference(mainMail).child(CONTACT_PATH).child(childKey);
    }
    public DatabaseReference getChatsReference(String receiver){

        String keySender = getAuthUserEmail().replace(".","_");
        String keyReceiver = receiver.replace(".","_");

        String keyChat = keySender + SEPERATOR + keyReceiver;

        if(keySender.compareTo(keyReceiver) > 0){
            keyChat = keyReceiver + SEPERATOR + keySender;
        }
        return databaseReference.getRoot().child(CHATS_PATH).child(keyChat);
    }
    public void changeUserConnectionStatus(boolean online){
        if(getMyUserReference() != null){
            Map<String,Object> updates = new HashMap<String, Object>();
            updates.put("online",online);
            getMyUserReference().updateChildren(updates);

            notifyContactsOfConnectionChange(online);
        }
    }
    public void notifyContactsOfConnectionChange(final boolean online,final boolean signoff){
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    String email = child.getKey();
                    DatabaseReference reference = getOneContactReference(email,myEmail);
                    reference.setValue(online);
                }
                if(signoff){
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void notifyContactsOfConnectionChange(boolean online){
        notifyContactsOfConnectionChange(online,false);
    }
    public void signOff(){
        notifyContactsOfConnectionChange(User.OFFLINE,true);
    }
}
