package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplateActivity extends AppCompatActivity {
    public ArrayList<Template> templates;
    public RecyclerView templateRv;
    public  TemplateAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        getSupportActionBar().setTitle("Template");
        handleAdd();
        setData();
        setupTemplateRv();
        fetchTemplate();
        

    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,AddEditTemplateActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplate();
    }



    private void fetchTemplate() {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<List<Template>> call = templateService.fetchTemplate();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);

            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplateActivity.this, "Fail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupTemplateRv() {
        templateRv = findViewById(R.id.message_rv);
        templateRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplateAdapter();
        templatesAdapter.setData(templates);
        templateRv.setAdapter(templatesAdapter);
    }

    private void setData() {
        templates = new ArrayList<>();
        Template template = new Template();
        template.message = "Hi";
        templates.add(template);

    }
}