package com.example.chatappfirebase.chat.ui;

import com.example.chatappfirebase.chat.entities.ChatMessage;

public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage message);
}
