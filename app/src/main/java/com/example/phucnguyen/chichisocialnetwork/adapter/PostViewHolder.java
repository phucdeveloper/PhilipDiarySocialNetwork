package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.model.PostWithImage;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithText;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;

public abstract class PostViewHolder extends RecyclerView.ViewHolder{

    abstract void sendData(Timeline timeline, int position, Context context, User user);

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
