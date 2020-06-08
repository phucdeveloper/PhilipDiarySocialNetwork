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

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.AccountDefaultActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.PostOneImageDetailActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.PostShareActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.PostTextBackgroundDetailActivity;
import com.example.phucnguyen.chichisocialnetwork.callback.OnLikeClickListener;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostTextWithBackgroundViewHolder extends PostViewHolder {

    private TextView txtContentPost, txtTimePost, txtNameAccount, txtNumberFavorite;
    private ImageView imgAvatar, imgLike, imgBackground;
    private Button btnComment, btnShare, btnLike;
    private ImageButton imgButtonMenu;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private double dem = 0;
    private OnLikeClickListener onLikeClickListener;

    @Override
    void sendData(final Timeline timeline, final int position, final Context context, final User user) {

        String idUserPost = timeline.getPostText().getIdUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        databaseReference.child(idUserPost).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final User user1 = dataSnapshot.getValue(User.class);
                Glide.with(context).load(user1.getAvatar()).override(300, 300).into(imgAvatar);
                txtNameAccount.setText(user1.getNickname());
                txtTimePost.setText(timeline.getPostText().getTimeCreate());

                Glide.with(context).load(timeline.getPostText().getBackground()).into(imgBackground);
                txtContentPost.setText(timeline.getPostText().getContentPost());

                if (timeline.getPostText().getContentPost().length() < 100) {
                    txtContentPost.setTextSize(25);
                    txtContentPost.setText(timeline.getPostText().getContentPost());
                } else {
                    txtContentPost.setTextSize(18);
                    txtContentPost.setText(timeline.getPostText().getContentPost());
                }

                btnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PostTextBackgroundDetailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userPost", user1);
                        bundle.putSerializable("user", user);
                        bundle.putSerializable("timeline", timeline);
                        intent.putExtra("data", bundle);
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (timeline.getPostText().getNumberFavorite() != 0) {
            imgLike.setImageResource(R.drawable.ic_thumb_up);
            txtNumberFavorite.setText(String.valueOf(timeline.getPostText().getNumberFavorite()));
            btnLike.setTextColor(context.getColor(R.color.color));
            btnLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

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
                double numberlike = dem + timeline.getPostText().getNumberFavorite();
                updateNumberFavoriteOfPost(timeline.getPostText().getIdUser(), timeline.getPostText().getIdPost(), numberlike);
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
                String id = timeline.getPostText().getIdUser();
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

    public PostTextWithBackgroundViewHolder(@NonNull View itemView) {
        super(itemView);

        imgAvatar = itemView.findViewById(R.id.imageview_avatar);
        txtContentPost = itemView.findViewById(R.id.textview_text);
        txtTimePost = itemView.findViewById(R.id.textview_time);
        txtNameAccount = itemView.findViewById(R.id.textview_text_nickname);
        btnComment = itemView.findViewById(R.id.button_comment);
        btnShare = itemView.findViewById(R.id.button_share);
        btnLike = itemView.findViewById(R.id.button_like);
        imgLike = itemView.findViewById(R.id.imageview_like);
        txtNumberFavorite = itemView.findViewById(R.id.textview_number_favorite);
        imgButtonMenu = itemView.findViewById(R.id.imagebutton_menu_text);
        imgBackground = itemView.findViewById(R.id.imageview_background);
    }

    private void updateNumberFavoriteOfPost(String idUser, String idPost, double number) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Posts");
        databaseReference.child(idUser).child(idPost).child("postText").child("numberFavorite").setValue(number);
    }
}
