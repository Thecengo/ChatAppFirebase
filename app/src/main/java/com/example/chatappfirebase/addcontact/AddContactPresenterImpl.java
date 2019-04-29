package com.example.chatappfirebase.addcontact;

import com.example.chatappfirebase.addcontact.event.AddContactEvent;
import com.example.chatappfirebase.addcontact.ui.AddContactView;
import com.example.chatappfirebase.lib.EventBus;
import com.example.chatappfirebase.lib.GreenRobotEventBus;

public class AddContactPresenterImpl implements AddContactPresenter{

    EventBus eventBus;
    AddContactView addContactView;
    AddContactInteractor addContactInteractor;

    public AddContactPresenterImpl(AddContactView addContactView){
        this.eventBus = GreenRobotEventBus.getInstance();
        this.addContactView = addContactView;
        this.addContactInteractor = new AddContactInteractorImpl(new AddContactRepositoryImpl());
    }
    @Override
    public void onShow() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        addContactView = null;
        eventBus.unRegister(this);

    }

    @Override
    public void addContact(String email) {
        addContactView.hideInput();
        addContactView.showProgress();
        this.addContactInteractor.addContact(email);

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

        if(addContactView != null){
            addContactView.hideProgress();
            addContactView.showInput();
            if(event.isError())
                addContactView.contactNotAdded();
            else
                addContactView.contactAdded();
        }
    }
}
