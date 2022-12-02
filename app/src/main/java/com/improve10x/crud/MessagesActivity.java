package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        setupMessagesListRv();
        fetchMessages();
        
    }

    private void fetchMessages() {
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService =messagesApi.createMessagesService();
        Call<List<Messages>> call = messagesService.fetchMessages();
        call.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                List<Messages> Lists = response.body();
                messageListsAdapter.setData(Lists);
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {

            }
        });

    }

    private void setupMessagesListRv() {
        messagesListsRv = findViewById(R.id.messages_rv);
        messagesListsRv.setLayoutManager(new LinearLayoutManager(this));
        messageListsAdapter = new MessagesAdapter();
        messageListsAdapter.setData(messagesLists);
        messagesListsRv.setAdapter(messageListsAdapter);
    }

    private void setData() {
        messagesLists = new ArrayList<>();
        Messages message = new Messages();
        message.name = "Aravind";
        message.phoneNumber = "+919000540052";
        message.message = " Hi, Welcome to Improve 10X.";
        messagesLists.add(message);

        Messages messages1 = new Messages();
        messages1.name = "Ramesh";
        messages1.phoneNumber = "+91 7893256197";
        messages1.message = "Welcome to improve 10X";
        messagesLists.add(message);




    }
}