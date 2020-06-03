package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.PostThreeImageDetailActivity;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostUpdateBackgroundViewHolder extends PostViewHolder {

    ImageView imgBackgroundUpdate, imgAvatar;
    TextView txtNameAccount, txtTimePost, txtStatus;
    ImageButton imgButtonMenu;
    Button btnComment, btnLike;
    TextView txtNumberFavorite;

    int dem = 0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public PostUpdateBackgroundViewHolder(@NonNull View itemView) {
        super(itemView);

        imgBackgroundUpdate = itemView.findViewById(R.id.item_imageview_background_update);
        imgAvatar = itemView.findViewById(R.id.imageview_avatar);
        txtNameAccount = itemView.findViewById(R.id.textview_text_nickname);
        txtTimePost = itemView.findViewById(R.id.textview_time);
        btnComment = itemView.findViewById(R.id.button_comment);
        txtStatus = itemView.findViewById(R.id.item_textview_status);
        btnLike = itemView.findViewById(R.id.button_like);
        btnComment = itemView.findViewById(R.id.button_comment);
        txtNumberFavorite = itemView.findViewById(R.id.textview_number_favorite);
        imgButtonMenu = itemView.findViewById(R.id.imagebutton_menu_image);
    }

    @Override
    void sendData(final Timeline timeline, int position, final Context context, User user) {
        Glide.with(context).load(user.getBackground()).into(imgBackgroundUpdate);
        Glide.with(context).load(user.getAvatar()).into(imgAvatar);
        txtNameAccount.setText(user.getNickname());
        txtTimePost.setText(timeline.getPostImage().getTimeCreate());
        txtStatus.setText(timeline.getPostImage().getStatusLine());

        firebaseDatabase = FirebaseDatabase.getInstance();
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem++;
                btnLike.setTextColor(context.getColor(R.color.color));
                databaseReference = firebaseDatabase.getReference().child("Posts");
                databaseReference.child(timeline.getPostImage().getIdPost())
                        .child("postImage")
                        .child("numberFavorite").setValue(dem);
                dem = 0;
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostThreeImageDetailActivity.class);
                intent.putExtra("data", timeline);
                context.startActivity(intent);
            }
        });

    }
}
