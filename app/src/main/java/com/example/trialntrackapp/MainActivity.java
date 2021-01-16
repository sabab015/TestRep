package com.example.trialntrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText username,password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.edUsername); //these findViewById finds all the views by the id's that we generated on xml file
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            //setOnClickListener is connecting a listener with certain attributes

            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this,"Username/Password Required", Toast.LENGTH_LONG).show(); //toast is using to show a message for a longer time
                } else {
                    // proceed to login
                    login();
                }
            }
        });

    }

    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString()); //getting username from button that was created in xml file
        loginRequest.setPassword(password.getText().toString()); //getting password from button that was created in xml file
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successfull", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LoginResponse loginResponse = response.body();
                            startActivity(new Intent(MainActivity.this,DashboardActivity.class).putExtra("data",loginResponse.getUsername()));

                        }
                    },700); //if input data is correct, system will redirect the user to a new activity
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_LONG).show(); //if user inputs wrong credentials, login failed text will be shown
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show(); //if API is failed to connect to server, error message will be shown

            }
        });
    }
}