package com.example.chatappfirebase.chat.entities;

import com.google.firebase.database.Exclude;

public class ChatMessage {
    private String message;
    private String sender;
    @Exclude
    private
    boolean sendByMe;

    public ChatMessage(String sender,String message){
        this.setSender(sender);
        this.setMessage(message);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSendByMe() {
        return sendByMe;
    }

    public void setSendByMe(boolean sendByMe) {
        this.sendByMe = sendByMe;
    }
}
