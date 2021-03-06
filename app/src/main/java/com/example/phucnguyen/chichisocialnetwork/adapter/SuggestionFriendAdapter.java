package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;

import java.util.ArrayList;

public class SuggestionFriendAdapter extends RecyclerView.Adapter<SuggestionFriendAdapter.ViewHolder> {

    ArrayList<User> arrayList;
    Context context;
    User user;
    OnInviteFriendClickListener onInviteFriendClickListener;


    public SuggestionFriendAdapter(ArrayList<User> arrayList, Context context, User user) {
        this.arrayList = arrayList;
        this.context = context;
        this.user = user;
    }

    public void setOnInviteFriendClickListener(OnInviteFriendClickListener onInviteFriendClickListener) {
        this.onInviteFriendClickListener = onInviteFriendClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invite_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).load(arrayList.get(position).getAvatar()).into(holder.imgAvatarInvite);
        holder.txtNicknameInvite.setText(arrayList.get(position).getNickname());

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onInviteFriendClickListener != null) {
                    onInviteFriendClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatarInvite;
        TextView txtNicknameInvite;
        Button btnConfirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarInvite = itemView.findViewById(R.id.item_imageview_avatar_invite);
            txtNicknameInvite = itemView.findViewById(R.id.item_texview_nickname_invite);
            btnConfirm = itemView.findViewById(R.id.button_confirm);
        }
    }

    public interface OnInviteFriendClickListener {
        void onItemClick(int position);
    }
}
