package com.example.chatappfirebase.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.chatappfirebase.chat.entities.ChatMessage;
import com.example.chatappfirebase.chat.events.ChatEvent;
import com.example.chatappfirebase.domain.FirebaseHelper;
import com.example.chatappfirebase.lib.EventBus;
import com.example.chatappfirebase.lib.GreenRobotEventBus;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class ChatRespositoryImpl implements ChatRepository {

    private String receiver;
    private FirebaseHelper firebaseHelper;
    private ChildEventListener chatEventListener;

    public ChatRespositoryImpl(){
        firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void sendMessage(String message) {
        String keySender = firebaseHelper.getAuthUserEmail().replace(".","_");
        ChatMessage chatMessage = new ChatMessage(keySender,message);
        DatabaseReference chatsReference = firebaseHelper.getChatsReference(receiver);
        chatsReference.push().setValue(chatMessage);

    }

    @Override
    public void setReceiver(String receiver) {

        this.receiver = receiver;
    }

    @Override
    public void destroyChatListener() {
        chatEventListener = null;

    }

    @Override
    public void subscribeForUpdates() {

        if(chatEventListener==null){
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String messageSender = chatMessage.getSender();
                    messageSender = messageSender.replace("_",".");

                    String currentUserEmail = firebaseHelper.getAuthUserEmail();
                    chatMessage.setSendByMe(messageSender.equals(currentUserEmail));

                    ChatEvent chatEvent = new ChatEvent(chatMessage);
                    EventBus eventBus = GreenRobotEventBus.getInstance();
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            };
            firebaseHelper.getChatsReference(receiver).addChildEventListener(chatEventListener);
        }

    }

    @Override
    public void unSubscribeForChatUpdates() {
        if(chatEventListener != null)
            firebaseHelper.getChatsReference(receiver).removeEventListener(chatEventListener);

    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        firebaseHelper.changeUserConnectionStatus(online);

    }
}
