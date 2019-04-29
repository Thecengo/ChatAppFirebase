package com.example.chatappfirebase.contactlist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chatappfirebase.AndroidChatApplication;
import com.example.chatappfirebase.R;
import com.example.chatappfirebase.addcontact.ui.AddContactFragment;
import com.example.chatappfirebase.chat.ui.ChatActivity;
import com.example.chatappfirebase.contactlist.ContactListPresenter;
import com.example.chatappfirebase.contactlist.ContactListPresenterImpl;
import com.example.chatappfirebase.contactlist.adapters.ContactListAdapter;
import com.example.chatappfirebase.contactlist.entities.User;
import com.example.chatappfirebase.lib.ImageLoader;
import com.example.chatappfirebase.login.ui.LoginActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView,OnItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewContacts)
    RecyclerView recyclerView;

    private ContactListAdapter adapter;
    private ContactListPresenter contactListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ButterKnife.bind(this);

        contactListPresenter = new ContactListPresenterImpl(this);
        contactListPresenter.onCreate();

        toolbar.setSubtitle(contactListPresenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);


    }
    public void setupAdapter(){
        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        adapter = new ContactListAdapter(new ArrayList<User>(),imageLoader,this);

    }
    public void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_contactlist,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        if(id == R.id.action_logout){
            contactListPresenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
            |Intent.FLAG_ACTIVITY_NEW_TASK
            |Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @OnClick(R.id.fab)
    public void addContact(){
        AddContactFragment addContactFragment = new AddContactFragment();
        addContactFragment.show(getSupportFragmentManager(),"");
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);

    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);

    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);

    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAİL_KEY,user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY,user.isOnline());
        startActivity(intent);

    }

    @Override
    public void onItemLongClick(User user) {
        contactListPresenter.removeContact(user.getEmail());

    }
}
