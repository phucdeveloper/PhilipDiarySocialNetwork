package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.CommentAdapter;
import com.example.phucnguyen.chichisocialnetwork.firebase.GetDataFromFirebase;
import com.example.phucnguyen.chichisocialnetwork.model.Comment;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class PostTextDetailActivity extends AppCompatActivity {

    TextView txtContentPost, txtNameAccount, txtTimeCreate;
    ImageView imgAvatar, imgAvatarComment, imgDisplayImage;
    ImageButton imgbuttonComment, imgbuttonCamera;
    EditText edtInputComment;
    RecyclerView recyclerViewListComment;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    String idPost;
    boolean isPicture = false;
    static int REQUESTCODE = 123;
    Timeline timeline;
    User user, userPost;
    Comment comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_text_detail);

        initView();

        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle != null) {
            timeline = (Timeline) bundle.getSerializable("timeline");
            user = (User) bundle.getSerializable("user");
            userPost = (User) bundle.getSerializable("userPost");
        }

        if (timeline == null) {
            Toast.makeText(PostTextDetailActivity.this, "Khong nhan duoc du lieu nao ca", Toast.LENGTH_SHORT).show();
        } else {
            idPost = timeline.getPostText().getIdPost();
            txtNameAccount.setText(userPost.getNickname());
            txtTimeCreate.setText(timeline.getPostText().getTimeCreate());
            Glide.with(PostTextDetailActivity.this).load(userPost.getAvatar()).into(imgAvatar);
            Glide.with(PostTextDetailActivity.this).load(user.getAvatar()).into(imgAvatarComment);
            txtContentPost.setText(timeline.getPostText().getContentPost());
        }

        imgbuttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPicture = true;
                openGallery();
            }
        });

        imgbuttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPicture) {
                    String contentComment = edtInputComment.getText().toString();
                    String keyComment = UUID.randomUUID().toString();
                    Comment comment = new Comment(keyComment, user.getNickname(), user.getAvatar(),
                            contentComment, 0, Constant.ITEM_COMMENT_TEXT);
                    insertToDatabase(idPost, comment.getIdComment(), comment);
                    edtInputComment.clearFocus();
                    edtInputComment.setSelection(0);
                } else {
                    insertToDatabase(idPost, comment.getIdComment(), comment);
                    //  imgDisplayImage.clearColorFilter();
                    isPicture = false;
                }
            }
        });

        setUpRecyclerViewListComment(idPost);
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUESTCODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null) {
            Uri uri = data.getData();
            imgDisplayImage.setImageURI(uri);
            sendCommentWithImage(uri);
        }
    }

    private void sendCommentWithImage(Uri uri) {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("image_comment");
        final StorageReference imageFilePath = storageReference.child(uri.getLastPathSegment());
        imageFilePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String linkImage = uri.toString();
                        String keyComment = UUID.randomUUID().toString();
                        comment = new Comment(keyComment, user.getNickname(), user.getAvatar(),
                                linkImage, 0, Constant.ITEM_COMMENT_IMAGE);
                    }
                });
            }
        });
    }

    private void initView() {
        txtContentPost = findViewById(R.id.textview_content_post_detail);
        txtNameAccount = findViewById(R.id.textview_text_nickname_detail);
        txtTimeCreate = findViewById(R.id.textview_time_detail);
        imgAvatar = findViewById(R.id.imageview_avatar_detail);
        edtInputComment = findViewById(R.id.edittext_input_comment);
        imgbuttonComment = findViewById(R.id.imagebutton_send_comment);
        recyclerViewListComment = findViewById(R.id.recyclerview_list_comment);
        imgbuttonCamera = findViewById(R.id.imagebutton_camera);
        imgDisplayImage = findViewById(R.id.imageview_display_image);
        imgAvatarComment = findViewById(R.id.imageview_avatar_comment);
    }

    private void setUpRecyclerViewListComment(final String idPost) {
        recyclerViewListComment.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(PostTextDetailActivity.this, RecyclerView.VERTICAL, true);
        recyclerViewListComment.setLayoutManager(layoutManager);

        final ArrayList<Comment> arrayList = new ArrayList<>();
        if (idPost == null) {
        } else {
            databaseReference = firebaseDatabase.getReference().child("Comments").child(idPost);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Comment comment = data.getValue(Comment.class);
                        arrayList.add(comment);
                    }
                    final CommentAdapter adapter = new CommentAdapter(arrayList, PostTextDetailActivity.this);
                    recyclerViewListComment.setAdapter(adapter);

                    adapter.setOnReplyClickListener(new CommentAdapter.OnReplyClickListener() {
                        @Override
                        public void OnReplyClick(int position) {
                            edtInputComment.requestFocus();
                            edtInputComment.setSelection(0);
                            String replyComment = edtInputComment.getText().toString();
                            Comment comment = new Comment(arrayList.get(position).getIdComment(), user.getNickname(),
                                    user.getAvatar(), replyComment, 0, Constant.ITEM_COMMENT_TEXT);
                            insertToDatabase(idPost, comment.getIdComment(), comment);
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(PostTextDetailActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void insertToDatabase(String keyPost, String keyComment, Comment comment) {
        databaseReference = firebaseDatabase.getReference().child("Comments").child(keyPost);
        databaseReference.child(keyComment).setValue(comment);
    }
}
