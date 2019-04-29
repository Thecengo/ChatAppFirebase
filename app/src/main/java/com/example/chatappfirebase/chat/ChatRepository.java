package com.example.chatappfirebase.chat;

public interface ChatRepository {
    void sendMessage(String message);
    void setReceiver(String receiver);

    void destroyChatListener();
    void subscribeForUpdates();
    void unSubscribeForChatUpdates();

    void changeUserConnectionStatus(boolean online);
}
