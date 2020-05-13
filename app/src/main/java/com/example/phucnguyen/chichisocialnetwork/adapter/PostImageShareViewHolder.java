package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;

import java.util.ArrayList;

public class PostImageShareViewHolder extends PostViewHolder{

    TextView txtNickname, txtTimeCreate, txtContentPost, txtTimePostShared, txtNicknamePostShared;
    ImageView imgAvatar, imgAvatarPostShared;
    ViewPager viewPager;
    Button btnLike, btnComment, btnShare;
    ImageButton imgButtonMenu;

    public PostImageShareViewHolder(@NonNull View itemView) {
        super(itemView);
        txtNickname = itemView.findViewById(R.id.textview_text_nickname);
        txtTimeCreate = itemView.findViewById(R.id.textview_time);
        imgAvatar = itemView.findViewById(R.id.imageview_avatar);
        txtContentPost = itemView.findViewById(R.id.textview_text);
        btnLike = itemView.findViewById(R.id.button_like);
        btnComment = itemView.findViewById(R.id.button_comment);
        btnShare = itemView.findViewById(R.id.button_share);
        imgButtonMenu = itemView.findViewById(R.id.imagebutton_menu_text_shared);

        viewPager = itemView.findViewById(R.id.view_pager);
        txtNicknamePostShared = itemView.findViewById(R.id.textview_nickname_post_shared);
        txtTimePostShared = itemView.findViewById(R.id.textview_time_post_shared);
        imgAvatarPostShared = itemView.findViewById(R.id.imageview_avatar_post_shared);
    }

    @Override
    void sendData(final Timeline timeline, int position, final Context context, final User user) {
        //Thong tin cua bai viet chia se
        txtContentPost.setText(timeline.getPostText().getContentPost());
        txtTimeCreate.setText(timeline.getPostText().getTimeCreate());
        txtNickname.setText(user.getNickname());
        Glide.with(context).load(user.getAvatar()).into(imgAvatar);


        //Thong tin bai viet duoc chia se
        ArrayList<String> arrayList = timeline.getPostImageShared().getArrayList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(arrayList, context);
        viewPager.setAdapter(adapter);
        txtNicknamePostShared.setText(user.getNickname());
      //  txtTimePostShared.setText(timeline.getPostImageShared().getTimeCreate());
      //  Glide.with(context).load(timeline.getPostImageShared().getAvatarAccount()).into(imgAvatarPostShared);
    }
}
