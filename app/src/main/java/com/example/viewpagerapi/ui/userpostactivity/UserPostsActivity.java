package com.example.viewpagerapi.ui.userpostactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.viewpagerapi.App;
import com.example.viewpagerapi.R;
import com.example.viewpagerapi.data.model.PostModel;
import com.example.viewpagerapi.data.network.PostService;

import java.util.ArrayList;

public class UserPostsActivity extends AppCompatActivity {

    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        if (getIntent() != null) {
            id = getIntent().getIntExtra("userId", 0);
        }

        App.postService.getUserPosts(id, new PostService.PostArrayCallback() {
            @Override
            public void onSucces(ArrayList<PostModel> postModel) {
                Log.e("POST", "onSucces: " + postModel );
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("POST", "onSucces: " + e );
            }
        });


    }
}