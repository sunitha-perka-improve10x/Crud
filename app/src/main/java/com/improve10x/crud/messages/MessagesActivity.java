package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

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
        handleAddBtn();
        setData();
        setupMessagesListRv();

    }


    public void deleteMessage(Messages messages){
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Void> call = messagesService.deleteMessages(messages.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MessagesActivity.this, "Successfully Deleted Messages", Toast.LENGTH_SHORT).show();
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Failure Deleted Messages", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void handleAddBtn() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMessagesActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected  void  onResume() {
        super.onResume();
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
                Toast.makeText(MessagesActivity.this, "Fail", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setupMessagesListRv() {
        messagesListsRv = findViewById(R.id.messages_rv);
        messagesListsRv.setLayoutManager(new LinearLayoutManager(this));
        messageListsAdapter = new MessagesAdapter();
        messageListsAdapter.setData(messagesLists);
        messageListsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Messages messages) {
                Toast.makeText(MessagesActivity.this, "OnItem Clicked", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onItemDelete(Messages messages) {
                Toast.makeText(MessagesActivity.this, "OnItem Delete", Toast.LENGTH_SHORT).show();
                deleteMessage(messages);

            }

            @Override
            public void onItemEdit(Messages messages) {
                Toast.makeText(MessagesActivity.this, "OnItem Edit", Toast.LENGTH_SHORT).show();

            }
        });
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