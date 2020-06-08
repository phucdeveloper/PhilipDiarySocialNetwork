package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Friend;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFriendAdapter extends RecyclerView.Adapter<ListFriendAdapter.ViewHolder> {

    ArrayList<Friend> arrayList;
    Context context;
    int viewType;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public ListFriendAdapter(ArrayList<Friend> arrayList, Context context, int viewType) {
        this.arrayList = arrayList;
        this.context = context;
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case Constant.ITEM_FRIEND_IN_ACCOUNT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_in_account, parent, false);
                return new ViewHolder(view);
            case Constant.ITEM_FRIEND_IN_ALL_FRIEND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_in_all_friend, parent, false);
                return new ViewHolder(view);

            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        databaseReference.child(arrayList.get(position).getIdUser2()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User friend = dataSnapshot.getValue(User.class);
                holder.txtNameFriend.setText(friend.getNickname());
                Glide.with(context).load(friend.getAvatar()).into(holder.imgAvatarFriend);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
