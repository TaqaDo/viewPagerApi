package com.example.viewpagerapi.ui.users;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewpagerapi.App;
import com.example.viewpagerapi.R;
import com.example.viewpagerapi.adapters.IOnClickListener;
import com.example.viewpagerapi.adapters.UserAdapter.UserAdapter;
import com.example.viewpagerapi.data.model.PostModel;
import com.example.viewpagerapi.data.network.post_service.PostService;
import com.example.viewpagerapi.ui.userpostactivity.UserPostsActivity;

import java.util.ArrayList;


public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<PostModel> postModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        getUsersList();
        onClicks();
    }

    @Override
    public void onResume() {
        super.onResume();
        getUsersList();
    }

    private void onClicks() {
        userAdapter.setOnClickListener(new IOnClickListener() {
            @Override
            public void OnLongClick(int pos) {

            }

            @Override
            public void OnClick(int pos) {
                Intent intent = new Intent(requireActivity(), UserPostsActivity.class);
                intent.putExtra("userId", postModels.get(pos).getUser());
                startActivity(intent);
            }
        });
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.post_recyclerview_users);
        userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

    }


    private void getUsersList() {
        App.postService.getUsers(new PostService.PostArrayCallback() {
            @Override
            public void onSucces(ArrayList<PostModel> postModel) {
                postModels = postModel;
                userAdapter.setUserModelArrayList(postModel);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("TAG", "onFailure: " + e );
            }
        });
    }
}