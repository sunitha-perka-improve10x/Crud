package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TemplateActivity extends AppCompatActivity {
    public ArrayList<Template> templates;
    public RecyclerView templateRv;
    public  TemplateAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        getSupportActionBar().setTitle("Template");
        setData();
        setupTemplateRv();
        

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