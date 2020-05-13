package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.PostAdapter;
import com.example.phucnguyen.chichisocialnetwork.firebase.InsertDataToFirebase;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithImage;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;
import com.example.phucnguyen.chichisocialnetwork.utils.UserUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class AccountActivity extends AppCompatActivity {

    RecyclerView recyclerViewListFriend, recyclerViewListPostOfAccount;
    ImageButton imgButtonUpdatePhoto, imgButtonUpdateBackground, imgButtonSettingProfile;
    ImageView imgAvatar, imgBackground, imgAvatarPost;
    TextView txtNickname, txtWork, txtStudent, txtCountry, txtAddress;
    LinearLayout layoutAddPost;
    EditText edtSearch;
    Toolbar toolbar;

    static int REQUESTCODEPHOTO = 123, REQUESTCODEBACKGROUND = 456;
    String imagePath;
    User user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    InsertDataToFirebase insertData = new InsertDataToFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initView();

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        final Intent intent = getIntent();
        String idUser = intent.getStringExtra("UserID");

        if (idUser != null)
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    txtNickname.setText(user.getNickname());
                    edtSearch.setText(user.getNickname());
                    Glide.with(AccountActivity.this).load(user.getAvatar()).into(imgAvatar);
                    Glide.with(AccountActivity.this).load(user.getBackground()).into(imgBackground);
                    Glide.with(AccountActivity.this).load(user.getAvatar()).into(imgAvatarPost);
                    txtWork.setText(user.getWork());
                    txtStudent.setText(user.getLiteracy());
                    txtCountry.setText(user.getCountry());
                    txtAddress.setText(user.getAddress());

                    setUpRecyclerViewListPostOfAccount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AccountActivity.this, "Database Falied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //   setUpRecyclerViewListFriend();


        imgButtonUpdatePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(REQUESTCODEPHOTO);
            }
        });

        imgButtonUpdateBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(REQUESTCODEBACKGROUND);
            }
        });

        imgButtonSettingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSetting = new Intent(AccountActivity.this, SettingProfileActivity.class);
                intentSetting.putExtra("user", user);
                startActivity(intentSetting);
            }
        });

        layoutAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AccountActivity.this, CreateNewPostActivity.class);
                intent1.putExtra("user", user);
                startActivity(intent1);
            }
        });
    }

    private void openGallery(int requestcode) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, requestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODEPHOTO && data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    imgAvatar.setImageURI(uri);
                    insertImageToFirebaseStorage(uri, REQUESTCODEPHOTO);
                    //Tao bai viet cho su kien cap nhat anh dai dien
                    String idPost = String.valueOf(System.currentTimeMillis());
                    String timePost = DateFormat.getDateTimeInstance().format(new Date());
                    if(user.getSex().equals("Nữ")){
                        PostWithImage postImage = new PostWithImage(user.getUid(), idPost, timePost,
                                0, null, "đã cập nhật ảnh đại diện của cô ấy", imagePath);
                        Timeline timeline = new Timeline(postImage, Constant.ITEM_POST_UPDATE_AVATAR);
                        insertData.insertPostWithImageToFirebaseDatabase(user.getUid(), idPost, timeline);
                    }
                    else if(user.getSex().equals("Nam")){
                        PostWithImage postImage = new PostWithImage(user.getUid(), idPost, timePost,
                                0, null, "đã cập nhật ảnh đại diện của anh ấy", imagePath);
                        Timeline timeline = new Timeline(postImage, Constant.ITEM_POST_UPDATE_AVATAR);
                        insertData.insertPostWithImageToFirebaseDatabase(user.getUid(), idPost, timeline);
                    }
                    else{
                        PostWithImage postImage = new PostWithImage(user.getUid(), idPost, timePost,
                                0, null, "đã cập nhật ảnh đại diện của họ", imagePath);
                        Timeline timeline = new Timeline(postImage, Constant.ITEM_POST_UPDATE_AVATAR);
                        insertData.insertPostWithImageToFirebaseDatabase(user.getUid(), idPost, timeline);
                    }
                }
            }

            if (requestCode == REQUESTCODEBACKGROUND && data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    imgBackground.setImageURI(uri);
                    insertImageToFirebaseStorage(uri, REQUESTCODEBACKGROUND);
                    //Tao bai viet cho su kien cap nhat anh bia
                    String idPost = String.valueOf(System.currentTimeMillis());
                    String timePost = DateFormat.getDateTimeInstance().format(new Date());
                    if(user.getSex().equals("Nữ")){
                        PostWithImage postImage = new PostWithImage(user.getUid(), idPost, timePost,
                                0, null, "đã cập nhật ảnh bìa của cô ấy", imagePath);
                        Timeline timeline = new Timeline(postImage, Constant.ITEM_POST_UPDATE_BACKGROUND);
                        insertData.insertPostWithImageToFirebaseDatabase(user.getUid(), idPost, timeline);
                    }
                    else if(user.getSex().equals("Nam")){
                        PostWithImage postImage = new PostWithImage(user.getUid(), idPost, timePost,
                                0, null, "đã cập nhật ảnh bìa của anh ấy", imagePath);
                        Timeline timeline = new Timeline(postImage, Constant.ITEM_POST_UPDATE_BACKGROUND);
                        insertData.insertPostWithImageToFirebaseDatabase(user.getUid(), idPost, timeline);
                    }
                    else{
                        PostWithImage postImage = new PostWithImage(user.getUid(), idPost, timePost,
                                0, null, "đã cập nhật ảnh bìa của họ", imagePath);
                        Timeline timeline = new Timeline(postImage, Constant.ITEM_POST_UPDATE_BACKGROUND);
                        insertData.insertPostWithImageToFirebaseDatabase(user.getUid(), idPost, timeline);
                    }
                }
            }
        }
    }

    private void setUpRecyclerViewListFriend() {
        recyclerViewListFriend.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(AccountActivity.this, 3);
        recyclerViewListFriend.setLayoutManager(layoutManager);
    }

    private void setUpRecyclerViewListPostOfAccount() {
        recyclerViewListPostOfAccount.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(AccountActivity.this, RecyclerView.VERTICAL, false);
        recyclerViewListPostOfAccount.setLayoutManager(layoutManager);

        final ArrayList<Timeline> listPost = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("Posts").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Timeline timeline = data.getValue(Timeline.class);
                    listPost.add(timeline);
                }

                PostAdapter adapter = new PostAdapter(listPost, AccountActivity.this, user);
                recyclerViewListPostOfAccount.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void insertImageToFirebaseStorage(Uri uri, final int code) {
        storageReference = firebaseStorage.getReference().child("image_user");
        if (uri.getLastPathSegment() != null) {
            final StorageReference imagePostFilePath = storageReference.child(uri.getLastPathSegment());

            imagePostFilePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagePostFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String image = uri.toString();

                            //Cap nhat lai anh dai dien trong Firebase database
                            if (code == REQUESTCODEPHOTO) {
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                databaseReference.child(user.getUid()).child("avatar").setValue(image);
                            }
                            if (code == REQUESTCODEBACKGROUND) {
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                databaseReference.child(user.getUid()).child("background").setValue(image);
                            }
                        }
                    });
                }
            });
        }
    }

    private void initView() {
        recyclerViewListFriend = findViewById(R.id.recyclerview_list_friend);
        imgButtonUpdatePhoto = findViewById(R.id.imagebutton_update_photo);
        imgButtonUpdateBackground = findViewById(R.id.imagebutton_update_background);
        imgAvatar = findViewById(R.id.imageview_avatar);
        imgBackground = findViewById(R.id.imageview_background);
        recyclerViewListPostOfAccount = findViewById(R.id.recyclerview_list_post_of_account);
        txtNickname = findViewById(R.id.textview_text_nickname);
        imgButtonSettingProfile = findViewById(R.id.imagebutton_setting_profile);
        layoutAddPost = findViewById(R.id.layout_add_new_post);
        edtSearch = findViewById(R.id.edittext_search);
        txtAddress = findViewById(R.id.textview_address);
        txtCountry = findViewById(R.id.textview_country);
        txtStudent = findViewById(R.id.textview_student);
        txtWork = findViewById(R.id.textview_work);
        toolbar = findViewById(R.id.toolbar_account);
        imgAvatarPost = findViewById(R.id.avatar);
    }
}
