package com.example.chatappfirebase.contactlist.ui;

import com.example.chatappfirebase.contactlist.entities.User;

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
