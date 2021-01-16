package com.example.trialntrackapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    //This is the place where all calls are placed

    @POST("login/") //relative url
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest); //The login body will have login request
}
