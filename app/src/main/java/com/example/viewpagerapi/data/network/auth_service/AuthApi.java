package com.example.viewpagerapi.data.network.auth_service;

import com.example.viewpagerapi.data.model.AuthModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AuthApi {

    @GET("user")
    Call<AuthModel> auth(@Header("Authorization") String header);

}
