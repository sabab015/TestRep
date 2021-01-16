package com.example.trialntrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        username = findViewById(R.id.username);
        Intent intent = getIntent(); //intent is used for passing data from one activity to another
        if(intent.getExtras()!= null){
            String passedUsername = intent.getStringExtra("data");
            username.setText("Welcome to Trial N Track");

        }

    }
}