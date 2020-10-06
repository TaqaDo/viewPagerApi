package com.example.viewpagerapi;

import android.app.Application;

import com.example.viewpagerapi.data.network.PostService;

public class App extends Application {

   public static PostService postService;

    @Override
    public void onCreate() {
        super.onCreate();
        postService = new PostService();
    }
}
