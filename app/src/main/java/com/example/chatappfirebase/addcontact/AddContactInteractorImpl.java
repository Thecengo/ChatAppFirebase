package com.example.chatappfirebase.addcontact;

public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepositoryImpl addContactRepository;

    public AddContactInteractorImpl(AddContactRepositoryImpl addContactRepository){
        this.addContactRepository = addContactRepository;
    }
    @Override
    public void addContact(String email) {
       addContactRepository.addContact(email);
    }
}
