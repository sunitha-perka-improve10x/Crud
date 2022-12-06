package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        getSupportActionBar().setTitle("Add Edit Template");
        handleAdd();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText messageTxt = findViewById(R.id.message_txt);
            String message = messageTxt.getText().toString();
            createTemplate(message);
        });
    }

    private void createTemplate(String message) {
        Template template = new Template();
        template.message = message;
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Template> call = templateService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddEditTemplateActivity.this, "SuccessfullyAdded message", Toast.LENGTH_SHORT).show();
                    finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failuer", Toast.LENGTH_SHORT).show();

            }
        });
    }
}