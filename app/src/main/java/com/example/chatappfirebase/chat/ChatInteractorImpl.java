package com.example.chatappfirebase.chat;

public class ChatInteractorImpl implements ChatInteractor{
    ChatRepository chatRepository;

    public ChatInteractorImpl(){
        this.chatRepository= new ChatRespositoryImpl();
    }
    @Override
    public void sendMessage(String message) {
        chatRepository.sendMessage(message);

    }

    @Override
    public void setRecipient(String recipient) {
        chatRepository.setReceiver(recipient);
    }

    @Override
    public void destroyChatListener() {
        chatRepository.destroyChatListener();

    }

    @Override
    public void subscribeForChatUpdates() {
        chatRepository.subscribeForUpdates();

    }

    @Override
    public void unSubsribeForChatUpdates() {
        chatRepository.unSubscribeForChatUpdates();

    }
}
