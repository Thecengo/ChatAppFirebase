package com.example.chatappfirebase.chat;

public class ChatSessionInteractorImpl implements ChatSessionInteractor {

    ChatRepository chatRepository;

    public ChatSessionInteractorImpl(){
        this.chatRepository = new ChatRespositoryImpl();
    }
    @Override
    public void changeConnectionStatus(boolean online) {
        chatRepository.changeUserConnectionStatus(online);
    }
}
