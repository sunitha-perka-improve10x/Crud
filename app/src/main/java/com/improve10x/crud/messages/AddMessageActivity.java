package com.improve10x.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private EditText messageTxt;
    private Message message;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_messages);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
            showEditBtn();
            handleEdit();
        } else {
            getSupportActionBar().setTitle("Add Message");
            showAddBtn();
            handleAdd();
        }
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
      editBtn.setOnClickListener(view -> {
          String name = nameTxt.getText().toString();
          String phoneNumber = phoneNumberTxt.getText().toString();
          String messageText = messageTxt.getText().toString();
          Message updateMessage = createMessage(name,phoneNumber,messageText);
          updateMessage(message.id,updateMessage);
      });
    }

    private void updateMessage(String id, Message updateMessage) {
    Call<Void> call = crudService.updatedMessage(id,updateMessage);
    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            showToast("successfully Add Message");
            finish();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            showToast("Failed");
        }
    });
    }

    private void showData() {
        nameTxt.setText(message.name);
        phoneNumberTxt.setText(message.phoneNumber);
        messageTxt.setText(message.messageText);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String messageText = messageTxt.getText().toString();
            Message message = createMessage(name, phoneNumber, messageText);
            saveMessage(message);
        });
    }

    private  void setupViews() {
        editBtn =findViewById(R.id.edit_btn);
        addBtn = findViewById(R.id.add_btn);
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }


    private Message createMessage(String name, String phoneNumber, String message) {
        Message messagesList = new Message();
        messagesList.name = name;
        messagesList.phoneNumber = phoneNumber;
        messagesList.messageText = message;
        return messagesList;
    }

    private void saveMessage(Message message) {
        Call<Message> call = crudService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully Added Message");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("Failure");
            }
        });

    }

}