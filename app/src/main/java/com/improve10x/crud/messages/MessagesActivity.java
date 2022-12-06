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
    public ArrayList<Message> messages;
    public RecyclerView messagesRv;
    public MessagesAdapter messagesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        handleAdd();
        setupData();
        setupMessagesRv();
    }

    public void deleteMessage(Message messages) {
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Void> call = messagesService.deleteMessage(messages.id);
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

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMessages();
    }

    private void fetchMessages() {
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<List<Message>> call = messagesService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> Lists = response.body();
                messagesAdapter.setData(Lists);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Fail", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setupMessagesRv() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messages);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Message messages) {
                Toast.makeText(MessagesActivity.this, "OnItem Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Message messages) {
                Toast.makeText(MessagesActivity.this, "OnItem Delete", Toast.LENGTH_SHORT).show();
                deleteMessage(messages);
            }

            @Override
            public void onItemEdit(Message messages) {
                Toast.makeText(MessagesActivity.this, "OnItem Edit", Toast.LENGTH_SHORT).show();
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }

    private void setupData() {
        messages = new ArrayList<>();
       /* Message message = new Message();
        message.name = "Aravind";
        message.phoneNumber = "+919000540052";
        message.messageText = " Hi, Welcome to Improve 10X.";
        messages.add(message);

        Message messages1 = new Message();
        messages1.name = "Ramesh";
        messages1.phoneNumber = "+91 7893256197";
        messages1.messageText = "Welcome to improve 10X";
        messages.add(message);*/

    }
}