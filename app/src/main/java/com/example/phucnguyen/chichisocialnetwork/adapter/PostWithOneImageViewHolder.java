package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.AccountDefaultActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.PostImageDetailActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.PostOneImageDetailActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.PostShareActivity;
import com.example.phucnguyen.chichisocialnetwork.callback.OnLikeClickListener;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PostWithOneImageViewHolder extends PostViewHolder {

    private TextView txtNameAccount, txtTimePost, txtNumberFavorite, txtStatus, txtContentPost;
    private ImageView imgAvatar, imgLike, imgImage;
    private Button btnComment, btnShare, btnLike;
    private ImageButton imgButtonMenu;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private double dem = 0;
    private OnLikeClickListener onLikeClickListener;

    @Override
    void sendData(final Timeline timeline, final int position, final Context context, final User user) {

        Glide.with(context).load(user.getAvatar()).into(imgAvatar);
        txtNameAccount.setText(user.getNickname());
        txtTimePost.setText(timeline.getPostImage().getTimeCreate());
        Glide.with(context).load(timeline.getPostImage().getArrayList().get(0)).into(imgImage);

        if(!TextUtils.isEmpty(timeline.getPostImage().getStatusLine())){
            txtContentPost.setText(timeline.getPostImage().getStatusLine());
        }
        else{
            txtContentPost.setVisibility(View.GONE);
        }

        if (timeline.getPostImage().getNumberFavorite() != 0) {
            imgLike.setImageResource(R.drawable.ic_thumb_up);
            txtNumberFavorite.setText(String.valueOf(timeline.getPostImage().getNumberFavorite()));
            btnLike.setTextColor(context.getColor(R.color.color));
            btnLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostOneImageDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("timeline", timeline);
                intent.putExtra("data", bundle);
                context.startActivity(intent);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostShareActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("timeline", timeline);
                intent.putExtra("data", bundle);
                context.startActivity(intent);
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem++;
                imgLike.setImageResource(R.drawable.ic_thumb_up);
                double numberlike = dem + timeline.getPostImage().getNumberFavorite();
                updateNumberFavoriteOfPost(timeline.getPostImage().getIdUser(), timeline.getPostImage().getIdPost(), numberlike);
                txtNumberFavorite.setText(String.valueOf(numberlike));
                dem = 0;
                if (onLikeClickListener != null) {
                    onLikeClickListener.onLikeClickItem(position);
                }
            }
        });

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = timeline.getPostImage().getIdUser();
                if (!id.equals(user.getUid())) {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference().child("Users");
                    databaseReference.child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User defaultUser = dataSnapshot.getValue(User.class);
                            Intent intent = new Intent(context, AccountDefaultActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("default_user", defaultUser);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    public PostWithOneImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imgAvatar = itemView.findViewById(R.id.imageview_avatar);
        txtNameAccount = itemView.findViewById(R.id.textview_text_nickname);
        txtTimePost = itemView.findViewById(R.id.textview_time);
        btnComment = itemView.findViewById(R.id.button_comment);
        imgImage = itemView.findViewById(R.id.imageview_image);
        btnShare = itemView.findViewById(R.id.button_share);
        btnLike = itemView.findViewById(R.id.button_like);
        imgLike = itemView.findViewById(R.id.imageview_like);
        txtNumberFavorite = itemView.findViewById(R.id.textview_number_favorite);
        imgButtonMenu = itemView.findViewById(R.id.imagebutton_menu_image);
        txtStatus = itemView.findViewById(R.id.item_textview_status);
        txtContentPost = itemView.findViewById(R.id.item_textview_content_post);
    }

    private void updateNumberFavoriteOfPost(String idUser, String idPost, double number) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Posts");
        databaseReference.child(idUser).child(idPost).child("postImage").child("numberFavorite").setValue(number);
    }
}
