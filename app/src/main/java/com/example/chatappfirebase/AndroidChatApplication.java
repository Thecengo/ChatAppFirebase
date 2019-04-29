package com.example.chatappfirebase;

import android.app.Application;

import com.example.chatappfirebase.lib.GlideImageLoader;
import com.example.chatappfirebase.lib.ImageLoader;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidChatApplication extends Application {
    private ImageLoader imageLoader;
    @Override
    public void onCreate(){
        super.onCreate();
        setupFirebase();
        setupImageLoader();

    }
    private void setupImageLoader(){
        imageLoader = new GlideImageLoader(this);
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
