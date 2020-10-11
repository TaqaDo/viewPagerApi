package com.example.viewpagerapi.data.network.auth_service;

import com.example.viewpagerapi.data.network.auth_service.AuthApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService {

    private static AuthApi authApi;

    public static AuthApi getClient() {
        if (authApi == null) {
            return authApi = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(AuthApi.class);
        } else
            return authApi;
    }

}
