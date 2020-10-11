package com.example.viewpagerapi.ui.editactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.viewpagerapi.App;
import com.example.viewpagerapi.R;
import com.example.viewpagerapi.data.model.PostModel;
import com.example.viewpagerapi.data.network.post_service.PostService;

public class EditActivity extends AppCompatActivity {
    private EditText title, content, user, group;
    private Button btnEdit;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
        getData();
        onClicks();
        fillEdits();

    }

    private void fillEdits() {
        App.postService.getPost(id, new PostService.PostCallback() {
            @Override
            public void onSucces(PostModel postModel) {
                title.setText(postModel.getTitle());
                content.setText(postModel.getContent());
                user.setText(postModel.getUser().toString());
                group.setText(postModel.getGroup().toString());
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    private void onClicks() {

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostModel postModel = new PostModel(title.getText().toString(),
                        content.getText().toString(),
                        Integer.valueOf(user.getText().toString()),
                        Integer.valueOf(group.getText().toString()));
                updatePost(id, postModel);
            }
        });

    }

    private void updatePost(Integer id, PostModel postModel) {
        App.postService.updatePost(id, postModel, new PostService.PostCallback() {
            @Override
            public void onSucces(PostModel postModel) {
                finish();
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("failure", "onFailure: " + e);
            }
        });
    }

    private void getData() {
        if (getIntent() != null) {
            id = getIntent().getIntExtra("post", 0);
        }
    }

    private void init() {
        title = findViewById(R.id.edit_title_edit);
        content = findViewById(R.id.edit_content_edit);
        group = findViewById(R.id.edit_group_edit);
        user = findViewById(R.id.edit_user_edit);
        btnEdit = findViewById(R.id.btn_edit);
    }
}