package com.example.viewpagerapi.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapi.R;
import com.example.viewpagerapi.data.model.PostModel;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<PostModel> postModelArrayList = new ArrayList<>();

    IOnClickListener onClickListener;


    public void setOnClickListener(IOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setPostArray(ArrayList<PostModel> postModelArrayList) {
        this.postModelArrayList = postModelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(postModelArrayList.get(position).getTitle());
        holder.content.setText(postModelArrayList.get(position).getContent());
        holder.group.setText(postModelArrayList.get(position).getGroup().toString());
        holder.user.setText(postModelArrayList.get(position).getUser().toString());
    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView title;
        private TextView content;
        private TextView group;
        private TextView user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_title);
            content = itemView.findViewById(R.id.text_content);
            group = itemView.findViewById(R.id.text_group);
            user = itemView.findViewById(R.id.text_user);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClickListener.OnLongClick(getAdapterPosition());
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.OnClick(getAdapterPosition());
                }
            });
        }
    }
}
