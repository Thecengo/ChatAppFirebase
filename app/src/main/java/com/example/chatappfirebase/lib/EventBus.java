package com.example.chatappfirebase.lib;

public interface EventBus {

    void register(Object subscriber);

    void unRegister(Object subscriber);

    void post(Object event);

}
