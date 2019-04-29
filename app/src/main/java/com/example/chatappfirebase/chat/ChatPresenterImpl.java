package com.example.chatappfirebase.chat;

import com.example.chatappfirebase.chat.entities.ChatMessage;
import com.example.chatappfirebase.chat.events.ChatEvent;
import com.example.chatappfirebase.chat.ui.ChatView;
import com.example.chatappfirebase.contactlist.entities.User;
import com.example.chatappfirebase.lib.EventBus;
import com.example.chatappfirebase.lib.GreenRobotEventBus;

public class ChatPresenterImpl implements ChatPresenter {

    EventBus eventBus;
    ChatView chatView;
    ChatInteractor chatInteractor;
    ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl(ChatView chatView){
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatView = chatView;
        this.chatInteractor = new ChatInteractorImpl();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        chatInteractor.unSubsribeForChatUpdates();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);

    }

    @Override
    public void onResume() {
        chatInteractor.subscribeForChatUpdates();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);

    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        eventBus.unRegister(this);
        chatInteractor.destroyChatListener();
        chatView = null;

    }

    @Override
    public void setChatRecipient(String recipient) {
        this.chatInteractor.setRecipient(recipient);

    }

    @Override
    public void sendMessage(String message) {
        this.chatInteractor.sendMessage(message);

    }

    @Override
    public void onEventMainThread(ChatEvent chatEvent) {
        if(chatView != null){
            ChatMessage message = chatEvent.getChatMessage();
            chatView.onMessageReceived(message);
        }

    }
}
