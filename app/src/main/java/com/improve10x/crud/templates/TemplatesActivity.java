package com.improve10x.crud.templates;

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

public class TemplatesActivity extends AppCompatActivity {
    private  CrudService crudService;
     private ArrayList<Template> templates;
     private RecyclerView templatesRv;
     private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        getSupportActionBar().setTitle("Template");
        setupApiService();
        log("onCreate");
        handleAdd();
        setupData();
        setupTemplatesRv();
    }

    private void log(String message) {
        Log.i("TemplateActivity",message);
    }

    private  void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteTemplate(Template template) {
        Call<Void> call = crudService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast ("Successfully Deleted Template");
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Fail Deleted Template");
            }
        });
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchTemplates();
    }

    private void fetchTemplates() {
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {

            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                showToast("Fail");

            }
        });
    }

    private void setupTemplatesRv() {
        templatesRv = findViewById(R.id.message_rv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void OnItemClicked(Template template) {
                showToast("On Item Clicked");
            }

            @Override
            public void OnItemDelete(Template template) {
                showToast("On Item Delete");
                deleteTemplate(template);
            }

            @Override
            public void OnItemEdit(Template template) {
                showToast("On Item Edit");
            }
        });
        templatesRv.setAdapter(templatesAdapter);
    }

    private void setupData() {
        templates = new ArrayList<>();
        Template template = new Template();
        template.messageText = "Hi";
        templates.add(template);
    }
}