package com.improve10x.crud.templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.AddTemplateActivity;
import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {
    public ArrayList<Template> templates;
    public RecyclerView templatesRv;
    public TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        getSupportActionBar().setTitle("Template");
        handleAdd();
        setupData();
        setupTemplatesRv();

    }

    public void deleteTemplate(Template template) {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplateService();
        Call<Void> call = templatesService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully Deleted Template", Toast.LENGTH_SHORT).show();
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Fail Deleted Template", Toast.LENGTH_SHORT).show();

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
        fetchTemplates();
    }

    private void fetchTemplates() {
        TemplatesApi templateApi = new TemplatesApi();
        TemplatesService templateService = templateApi.createTemplateService();
        Call<List<Template>> call = templateService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);

            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Fail", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(TemplatesActivity.this, "On Item Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemDelete(Template template) {
                Toast.makeText(TemplatesActivity.this, "On Item Delete", Toast.LENGTH_SHORT).show();
                deleteTemplate(template);
            }

            @Override
            public void OnItemEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "On Item Edit", Toast.LENGTH_SHORT).show();
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