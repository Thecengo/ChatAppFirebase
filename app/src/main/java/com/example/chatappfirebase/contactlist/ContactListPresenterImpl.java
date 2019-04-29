package com.example.chatappfirebase.contactlist;

import com.example.chatappfirebase.contactlist.entities.User;
import com.example.chatappfirebase.contactlist.events.ContactListEvent;
import com.example.chatappfirebase.contactlist.ui.ContactListView;
import com.example.chatappfirebase.lib.EventBus;
import com.example.chatappfirebase.lib.GreenRobotEventBus;

public class ContactListPresenterImpl implements ContactListPresenter {

    EventBus eventBus;
    ContactListView contactListView;
    ContactListSessionInteractor contactListSessionInteractor;
    ContactListInteractor contactListInteractor;

    public ContactListPresenterImpl(ContactListView contactListView){

        this.eventBus = GreenRobotEventBus.getInstance();
        this.contactListView = contactListView;
        this.contactListSessionInteractor = new ContactListSessionInteractorImpl();
        this.contactListInteractor = new ContactListInteractorImpl();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        contactListSessionInteractor.changeConnectionStatus(User.ONLINE);
        contactListInteractor.subscribeForContactEvents();

    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        eventBus.unRegister(this);
        contactListInteractor.destroyContactListListener();
        contactListView = null;

    }

    @Override
    public void signOff() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.destroyContactListListener();
        contactListInteractor.unSubscribeForContactEvents();
        contactListSessionInteractor.signOff();

    }

    @Override
    public String getCurrentUserEmail() {
        return contactListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListInteractor.removeContact(email);

    }

    @Override
    public void onEventMainThread(ContactListEvent event) {

        User user = event.getUser();

        switch (event.getEventType()){
            case ContactListEvent.ON_CONTACT_ADDED:
                onContactAdded(user);
                break;
            case ContactListEvent.ON_CONTACT_CHANGED:
                onContactChanged(user);
                break;
             case ContactListEvent.ON_CONTACT_REMOVED:
                 onContactRemoved(user);
                 break;

        }
    }
    public void onContactAdded(User user){
        if(contactListView != null)
            contactListView.onContactAdded(user);
    }
    public void onContactChanged(User user){
        if(contactListView != null)
            contactListView.onContactChanged(user);
    }
    public void onContactRemoved(User user){
        if(contactListView!=null)
            contactListView.onContactRemoved(user);
    }
}
