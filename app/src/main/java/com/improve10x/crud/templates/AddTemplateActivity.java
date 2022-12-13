package com.improve10x.crud.templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        getSupportActionBar().setTitle("Add Template");
        setupViews();
        setupApiService();
        handleAdd();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupViews() {
         addBtn = findViewById(R.id.add_btn);
        messageTxt = findViewById(R.id.message_txt);

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

    private  void saveMessage(Template template) {
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