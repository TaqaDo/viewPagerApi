package com.example.viewpagerapi.adapters.UserAdapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapi.R;
import com.example.viewpagerapi.adapters.IOnClickListener;
import com.example.viewpagerapi.data.model.PostModel;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<PostModel> userModelArrayList = new ArrayList<>();
    public IOnClickListener onClickListener;

    public void setOnClickListener(IOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setUserModelArrayList(ArrayList<PostModel> userModelArrayList) {
        this.userModelArrayList = userModelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(userModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.userId2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.OnClick(getAdapterPosition());
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void onBind(PostModel model) {
            Log.e("TAG", "onBind: " + model.getUser().toString());
            textView.setText(model.getUser().toString());
        }
    }
}
