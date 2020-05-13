package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Friend;

import java.util.ArrayList;

public class ListFriendAdapter extends RecyclerView.Adapter<ListFriendAdapter.ViewHolder> {

    ArrayList<Friend> arrayList;
    Context context;


    public ListFriendAdapter(ArrayList<Friend> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.txtNameFriend.setText(arrayList.get(position).getName());
//        holder.imgAvatarFriend.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAvatarFriend;
        TextView txtNameFriend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatarFriend = itemView.findViewById(R.id.item_imageview_friend);
            txtNameFriend = itemView.findViewById(R.id.item_textview_friend);
        }
    }
}
