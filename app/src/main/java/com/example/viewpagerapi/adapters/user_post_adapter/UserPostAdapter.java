package com.example.viewpagerapi.adapters.user_post_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapi.R;
import com.example.viewpagerapi.data.model.PostModel;

import java.util.ArrayList;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.ViewHolder>  {

    private ArrayList<PostModel> modelArrayList = new ArrayList<>();

    public void setModelArrayList(ArrayList<PostModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserPostAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_post_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(modelArrayList.get(position).getTitle());
        holder.content.setText(modelArrayList.get(position).getContent());
        holder.user.setText(modelArrayList.get(position).getUser().toString());
        holder.group.setText(modelArrayList.get(position).getGroup().toString());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private TextView group;
        private TextView user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title_user);
            content = itemView.findViewById(R.id.text_content_user);
            group = itemView.findViewById(R.id.text_group_user);
            user = itemView.findViewById(R.id.text_user_user);
        }
    }
}
