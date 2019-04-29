package com.example.chatappfirebase.chat.events;

import com.example.chatappfirebase.chat.entities.ChatMessage;

public class ChatEvent {

    ChatMessage chatMessage;

    public ChatEvent(ChatMessage chatMessage){
        this.chatMessage = chatMessage;
    }
    public ChatMessage getChatMessage(){
        return this.chatMessage;
    }
}
