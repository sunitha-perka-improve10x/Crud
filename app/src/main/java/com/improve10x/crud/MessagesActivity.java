package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {
    public ArrayList<Messages> messagesLists;
    public RecyclerView messagesListsRv;
    public MessagesAdapter messageListsAdapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        setData();
        
    }

    private void setData() {
    }
}