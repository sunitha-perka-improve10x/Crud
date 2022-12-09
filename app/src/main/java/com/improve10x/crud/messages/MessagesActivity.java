package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {
    private CrudService crudService;
    private ArrayList<Message> messages;
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        setupApiService();
        log("onCreate");
        handleAdd();
        setupData();
        setupMessagesRv();
    }

    private void log(String message) {
        Log.i("MessagesActivity",message);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteMessage(Message messages) {
        Call<Void> call = crudService.deleteMessage(messages.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully Deleted");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed To Deleted");
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
        log("onResume");
        fetchMessages();
    }

    private void fetchMessages() {
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> Lists = response.body();
                messagesAdapter.setData(Lists);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                showToast("Fail");
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
                showToast("OnItem Clicked");
            }

            @Override
            public void onItemDelete(Message messages) {
                showToast("OnItem Delete");
                deleteMessage(messages);
            }

            @Override
            public void onItemEdit(Message messages) {
                showToast("OnItem Edit");
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