package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
        handler.postDelayed((() -> {
        }),5000);

    }
}