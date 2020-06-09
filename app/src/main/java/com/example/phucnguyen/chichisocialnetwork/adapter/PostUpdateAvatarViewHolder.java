package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostUpdateAvatarViewHolder extends PostViewHolder {

    ImageView imgAvatarUpdate, imgAvatar;
    TextView txtNameAccount, txtTimePost, txtStatus;
    ImageButton imgButtonMenu;
    Button btnComment, btnLike;
    TextView txtNumberFavorite;
    LinearLayout linearLayout;

    int dem = 0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public PostUpdateAvatarViewHolder(@NonNull View itemView) {
        super(itemView);

        imgAvatarUpdate = itemView.findViewById(R.id.item_imageview_avatar_update);
        imgAvatar = itemView.findViewById(R.id.imageview_avatar);
        txtNameAccount = itemView.findViewById(R.id.textview_text_nickname);
        txtTimePost = itemView.findViewById(R.id.textview_time);
        btnComment = itemView.findViewById(R.id.button_comment);
        txtStatus = itemView.findViewById(R.id.item_textview_status);
        btnLike = itemView.findViewById(R.id.button_like);
        btnComment = itemView.findViewById(R.id.button_comment);
        txtNumberFavorite = itemView.findViewById(R.id.textview_number_favorite);
        imgButtonMenu = itemView.findViewById(R.id.imagebutton_menu_image);
        linearLayout = itemView.findViewById(R.id.layout);
    }

    @Override
    void sendData(final Timeline timeline, int position, final Context context, User user) {
        linearLayout.setVisibility(View.GONE);

        Glide.with(context).load(user.getAvatar()).into(imgAvatarUpdate);
        Glide.with(context).load(user.getAvatar()).override(300, 300).into(imgAvatar);
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
                        .child("postImage").
                        child("numberFavorite").setValue(dem);
                dem = 0;
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Chức năng này đang trong quá trình chỉnh sửa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
