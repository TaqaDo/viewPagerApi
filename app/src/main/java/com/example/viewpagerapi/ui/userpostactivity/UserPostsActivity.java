package com.example.viewpagerapi.ui.userpostactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.viewpagerapi.App;
import com.example.viewpagerapi.R;
import com.example.viewpagerapi.adapters.user_post_adapter.UserPostAdapter;
import com.example.viewpagerapi.data.model.PostModel;
import com.example.viewpagerapi.data.network.post_service.PostService;

import java.util.ArrayList;

public class UserPostsActivity extends AppCompatActivity {

    private Integer id;
    private RecyclerView recyclerView;
    private UserPostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        init();
        getUserId();
        getUserPosts();
    }

    private void init() {
        recyclerView = findViewById(R.id.user_recycler);
        adapter = new UserPostAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getUserId() {
        if (getIntent() != null) {
            id = getIntent().getIntExtra("userId", 0);
            Log.e("TAG", "onCreate: " + id );
        }
    }

    private void getUserPosts() {
        App.postService.getUserPosts(id, new PostService.PostArrayCallback() {
            @Override
            public void onSucces(ArrayList<PostModel> postModel) {
                Log.e("POST", "onSucces: " + postModel );
                adapter.setModelArrayList(postModel);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("POST", "onSucces: " + e );
            }
        });
    }
}