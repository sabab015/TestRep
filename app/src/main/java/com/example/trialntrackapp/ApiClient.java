package com.example.trialntrackapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //this class is going to provide user service


    private static Retrofit getRetrofit(){
        //Retrofit is a REST Client for Java and Android

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8000/api/") //passing base url here
                .client(okHttpClient)
                .build();
        return retrofit;

    }

    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

}
