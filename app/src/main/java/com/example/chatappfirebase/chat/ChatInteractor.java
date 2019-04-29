package com.example.chatappfirebase.chat;

public interface ChatInteractor {
    void sendMessage(String message);
    void setRecipient(String recipient);

    void destroyChatListener();
    void subscribeForChatUpdates();
    void unSubsribeForChatUpdates();
}
