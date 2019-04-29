package com.example.chatappfirebase.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.chatappfirebase.AndroidChatApplication;
import com.example.chatappfirebase.R;
import com.example.chatappfirebase.chat.ChatPresenter;
import com.example.chatappfirebase.chat.ChatPresenterImpl;
import com.example.chatappfirebase.chat.adapters.ChatAdapter;
import com.example.chatappfirebase.chat.entities.ChatMessage;
import com.example.chatappfirebase.domain.AvatarHelper;
import com.example.chatappfirebase.lib.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView{

    public final static String EMAİL_KEY = "email";
    public final static String ONLINE_KEY ="online";

    @BindView(R.id.toolbarChat)
    Toolbar toolbar;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtStatus) TextView txtStatus;
    @BindView(R.id.editTextMessageChat)
    EditText inputMessage;
    @BindView(R.id.messageRecylerView)
    RecyclerView recyclerView;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;

    private ChatAdapter chatAdapter;
    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        setToolbarData(intent);

        setupAdapter();
        setupRecylerView();
    }
    @Override
    public void onResume(){
        super.onResume();
        chatPresenter.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        chatPresenter.onPause();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        chatPresenter.onDestroy();
    }

    @Override
    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        chatPresenter.sendMessage(inputMessage.getText().toString());
        inputMessage.setText("");

    }

    @Override
    public void onMessageReceived(ChatMessage message) {
        chatAdapter.add(message);
        recyclerView.scrollToPosition(chatAdapter.getItemCount()-1);

    }
    public void setToolbarData(Intent intent){
        String recipient = intent.getStringExtra(EMAİL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = intent.getBooleanExtra(ONLINE_KEY,false);
        String status = online ?"online":"offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
    }

    private void setupAdapter(){
        chatAdapter = new ChatAdapter(this,new ArrayList<ChatMessage>());
    }
    private void setupRecylerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);
    }


}
