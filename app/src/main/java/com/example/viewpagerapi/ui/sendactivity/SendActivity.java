package com.example.viewpagerapi.ui.sendactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.viewpagerapi.App;
import com.example.viewpagerapi.R;
import com.example.viewpagerapi.data.model.PostModel;
import com.example.viewpagerapi.data.network.post_service.PostService;

public class SendActivity extends AppCompatActivity {

    private EditText title, content, user, group;
    private Button btnSend;
    private PostModel postModel3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        init();
        onClicks();

    }

    private void onClicks() {
        btnSend.setOnClickListener(view -> {
            Log.e("ololo", "t");

            if (!title.getText().toString().isEmpty() && !content.getText().toString().isEmpty() && !group.getText().toString().isEmpty()) {
                postModel3 = new PostModel(title.getText().toString(), content.getText().toString(), 5, Integer.valueOf(group.getText().toString()));
                createPost(postModel3);
            } else {
                title.setError("заполните поле");
                content.setError("заполните поле");
                group.setError("заполните поле");
            }

        });
    }


    private void createPost(PostModel postModel3) {
        App.postService.createPost(postModel3, new PostService.PostCallback() {
            @Override
            public void onSucces(PostModel postModel) {
                Log.e("ololo", "onFailure: on create");
                finish();
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("ololo", "onFailure:", e);
            }
        });
    }

    private void init() {
        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.edit_content);
        group = findViewById(R.id.edit_group);
        user = findViewById(R.id.edit_user);
        btnSend = findViewById(R.id.btn_send);

    }
}