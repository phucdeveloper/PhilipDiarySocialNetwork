package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.model.Comment;

public abstract class CommentViewHolder extends RecyclerView.ViewHolder {

    abstract void sendData(Comment comment, int position, Context context);

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
