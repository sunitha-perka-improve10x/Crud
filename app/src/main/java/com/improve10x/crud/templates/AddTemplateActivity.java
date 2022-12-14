package com.improve10x.crud.templates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseActivity {
    private CrudService crudService;
    private Button addBtn;
    private EditText messageTxt;
    private Template template;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            showEditBtn();
            handleEdit();
        } else {
            getSupportActionBar().setTitle("Add Template");
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
            String message = messageTxt.getText().toString();
            Template updatedTemplate = createTemplate(message);
            updateTemplate(template.id, updatedTemplate);

        });
    }

    private void updateTemplate(String id, Template updatedTemplate) {
        Call<Void> call = crudService.updatedTemplate(id, updatedTemplate);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Success");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failure");
            }
        });
    }

    private void showData() {
        messageTxt.setText(template.messageText);
    }


    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        messageTxt = findViewById(R.id.message_txt);
        editBtn = findViewById(R.id.edit_btn);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String message = messageTxt.getText().toString();
            Template template = createTemplate(message);
            saveMessage(template);
        });
    }

    private Template createTemplate(String message) {
        Template template = new Template();
        template.messageText = message;
        return template;
    }

    private void saveMessage(Template template) {
        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("SuccessfullyAdded message");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                showToast("Failuer");

            }
        });
    }
}