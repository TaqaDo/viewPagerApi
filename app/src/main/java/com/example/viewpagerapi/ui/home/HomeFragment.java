package com.example.viewpagerapi.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.viewpagerapi.App;
import com.example.viewpagerapi.R;
import com.example.viewpagerapi.adapters.IOnClickListener;
import com.example.viewpagerapi.adapters.post_adapter.PostAdapter;
import com.example.viewpagerapi.data.model.PostModel;
import com.example.viewpagerapi.data.network.post_service.PostService;
import com.example.viewpagerapi.ui.editactivity.EditActivity;
import com.example.viewpagerapi.ui.sendactivity.SendActivity;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private Button addPost;
    private ArrayList<PostModel> postRecycler;
    private SwipeRefreshLayout refreshLayout;
    private Integer id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        getUsers();
        refreshList();
        onClicks();

    }

    @Override
    public void onResume() {
        super.onResume();
        getUsers();
    }

    private void onClicks() {
        postAdapter.setOnClickListener(new IOnClickListener() {
            @Override
            public void OnLongClick(int pos) {
                id = postRecycler.get(pos).getId();
                deletePost(id, pos);
            }

            @Override
            public void OnClick(int pos) {
                if (postRecycler.get(pos).getUser() == 5) {
                    Intent intent = new Intent(requireActivity(), EditActivity.class);
                    intent.putExtra("post", postRecycler.get(pos).getId());
                    startActivity(intent);
//                startActivityForResult(intent, SendActivity.UPDATE_POST);
                }
            }
        });


        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), SendActivity.class);
//                startActivityForResult(intent, SendActivity.ADD_POST);
                startActivity(intent);
            }
        });
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.post_recyclerview1);
        addPost = view.findViewById(R.id.btn_add_post);
        refreshLayout = view.findViewById(R.id.refresh);
        postAdapter = new PostAdapter();
        recyclerView.setAdapter(postAdapter);
    }


    private void refreshList() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void getUsers() {
        App.postService.getPosts(new PostService.PostArrayCallback() {
            @Override
            public void onSucces(ArrayList<PostModel> postModel) {
                postRecycler = postModel;
                postAdapter.setPostArray(postRecycler);
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("fail", e.getLocalizedMessage());
            }
        });
    }

    private void deletePost(Integer id, int pos) {
        App.postService.deletePost(id, new PostService.PostCallback() {
            @Override
            public void onSucces(PostModel postModel) {
                Toast.makeText(requireContext(), "Post Delete", Toast.LENGTH_SHORT).show();
                postRecycler.remove(pos);
                postAdapter.notifyItemRemoved(pos);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("fail", "onFailure: " + e );
            }
        });
    }
}