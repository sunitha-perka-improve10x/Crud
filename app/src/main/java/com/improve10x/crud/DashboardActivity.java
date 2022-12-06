package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.improve10x.crud.messages.MessagesActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        handleMessageBtn();
        handleTemplateBtn();
    }

    private void handleMessageBtn() {
        ImageButton imageBtn = findViewById(R.id.image_btn);
        imageBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MessagesActivity.class);
            startActivity(intent);
        });
    }

    private void handleTemplateBtn() {
        ImageButton templateBtn = findViewById(R.id.template_btn);
        templateBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, TemplatesActivity.class);
            startActivity(intent);
        });
    }
}