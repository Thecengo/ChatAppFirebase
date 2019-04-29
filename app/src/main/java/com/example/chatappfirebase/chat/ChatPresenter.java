package com.example.chatappfirebase.chat;

import com.example.chatappfirebase.chat.events.ChatEvent;

public interface ChatPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String message);
    void onEventMainThread(ChatEvent chatEvent);
}
