package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;

public class PostTextShareViewHolder extends  PostViewHolder{

    TextView txtNickname, txtTimeCreate, txtContentPost, txtContentPostShared, txtTimePostShared, txtNicknamePostShared;
    ImageView imgAvatar, imgAvatarPostShared;

    public PostTextShareViewHolder(@NonNull View itemView) {
        super(itemView);
        txtNickname = itemView.findViewById(R.id.textview_text_nickname);
        txtTimeCreate = itemView.findViewById(R.id.textview_time);
        imgAvatar = itemView.findViewById(R.id.imageview_avatar);
        txtContentPost = itemView.findViewById(R.id.textview_text);

        txtContentPostShared = itemView.findViewById(R.id.textview_content_post_shared);
        txtNicknamePostShared = itemView.findViewById(R.id.textview_nickname_post_shared);
        txtTimePostShared = itemView.findViewById(R.id.textview_time_post_shared);
        imgAvatarPostShared = itemView.findViewById(R.id.imageview_avatar_post_shared);
    }

    @Override
    void sendData(Timeline timeline, int position, Context context, User user) {
        //Thong tin cua bai viet chia se
        txtContentPost.setText(timeline.getPostText().getContentPost());
        txtTimeCreate.setText(timeline.getPostText().getTimeCreate());
        txtNickname.setText(user.getNickname());
        Glide.with(context).load(user.getAvatar()).into(imgAvatar);


        //Thong tin bai viet duoc chia se
    //    txtNicknamePostShared.setText(timeline.getPostTextShared().getNameAccount());
        txtTimePostShared.setText(timeline.getPostTextShared().getTimeCreate());
        txtContentPostShared.setText(timeline.getPostTextShared().getContentPost());
      //  Glide.with(context).load(timeline.getPostTextShared().getAvatarAccount()).into(imgAvatarPostShared);
    }
}
