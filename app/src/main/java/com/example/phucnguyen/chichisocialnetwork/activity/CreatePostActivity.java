package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.BackgroundAdapter;
import com.example.phucnguyen.chichisocialnetwork.adapter.ListImageAdapter;
import com.example.phucnguyen.chichisocialnetwork.firebase.InsertDataToFirebase;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithImage;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithText;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreatePostActivity extends AppCompatActivity {

    LinearLayout linearLayout, linearLayoutCreatePost;
    BottomSheetBehavior bottomSheetBehavior;
    EditText edtInput;
    TextView txtToPost, txtAddPost;
    RecyclerView recyclerViewListImage, recyclerViewListBackground;
    Button btnAlbum;
    ImageView imgAvatar, imgBackground;
    TextView txtNickname, txtImageVideo, txtBackground;
    VideoView videoView;

    boolean isImageVideo = false, isTextBackground = false;
    static int REQUESTCODEIMAGES = 123;
    static int REQUESTCODEVIDEOS = 345;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> listBackground;// = new ArrayList<>();
    ArrayList<Uri> listImage = new ArrayList<>();
    BackgroundAdapter adapter;
    Uri uri;
    InsertDataToFirebase insertDataToFirebase = new InsertDataToFirebase();
    User dataUser;
    String background;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        initView();

        videoView.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        dataUser = (User) intent.getSerializableExtra("user");
        Glide.with(CreatePostActivity.this).load(dataUser.getAvatar()).into(imgAvatar);
        txtNickname.setText(dataUser.getNickname());

        txtImageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isImageVideo = true;
                openGalleryImages();
            }
        });

        txtBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTextBackground = true;
                setUpRecyclerViewBackground();
            }
        });

        linearLayoutCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        txtToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeCreate = new SimpleDateFormat("dd 'tháng' MM 'lúc' HH:mm").format(new Date());
                if (isImageVideo) {
                    String contentPostImage = edtInput.getText().toString();
                    if (TextUtils.isEmpty(contentPostImage)) {
                        String idPost = String.valueOf(System.currentTimeMillis());
                        PostWithImage post = new PostWithImage(dataUser.getUid(), idPost, timeCreate, 0,
                                null, arrayList);
                        Timeline timeline = new Timeline(post, Constant.ITEM_POST_WITH_IMAGE);
                        insertDataToFirebase.insertPostWithImageToFirebaseDatabase(post.getIdUser(), post.getIdPost(), timeline);
                    } else {
                        String idPost = String.valueOf(System.currentTimeMillis());
                        PostWithImage post = new PostWithImage(dataUser.getUid(), idPost, timeCreate, 0,
                                null, contentPostImage, arrayList);
                        Timeline timeline = new Timeline(post, Constant.ITEM_POST_WITH_IMAGE);
                        insertDataToFirebase.insertPostWithImageToFirebaseDatabase(post.getIdUser(), post.getIdPost(), timeline);
                    }
                    arrayList.clear();
                } else {
                    if(isTextBackground){
                        String idPost = String.valueOf(System.currentTimeMillis());
                        String contentPost = edtInput.getText().toString();
                        PostWithText post = new PostWithText(dataUser.getUid(), idPost, timeCreate, 0, null, contentPost, background);
                        Timeline timeline = new Timeline(post, Constant.ITEM_POST_WITH_TEXT);
                        insertDataToFirebase.insertPostWithTextToFirebaseDatabae(post.getIdUser(), post.getIdPost(), timeline);
                        edtInput.clearFocus();
                    }
                    else{
                        String idPost = String.valueOf(System.currentTimeMillis());
                        String contentPost = edtInput.getText().toString();
                        PostWithText post = new PostWithText(dataUser.getUid(), idPost, timeCreate, 0, null, contentPost);
                        Timeline timeline = new Timeline(post, Constant.ITEM_POST_WITH_TEXT);
                        insertDataToFirebase.insertPostWithTextToFirebaseDatabae(post.getIdUser(), post.getIdPost(), timeline);
                        edtInput.clearFocus();
                    }
                }
                finish();
            }
        });
    }

    private void openGalleryImages() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUESTCODEIMAGES);
    }

    private void openGalleryVideos(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent, REQUESTCODEVIDEOS);
    }

    private void initView() {
        linearLayout = findViewById(R.id.layout_bottom_sheet);
        txtImageVideo = findViewById(R.id.textview_image_video);
        edtInput = findViewById(R.id.edittext_input);
        txtToPost = findViewById(R.id.textview_create_post);
        linearLayoutCreatePost = findViewById(R.id.linearlayout);
        txtAddPost = findViewById(R.id.textview_add_post);
        recyclerViewListImage = findViewById(R.id.recyclerview_list_image);
        btnAlbum = findViewById(R.id.button_album);
        txtNickname = findViewById(R.id.textview_nickname);
        imgAvatar = findViewById(R.id.imageview_avatar);
        videoView = findViewById(R.id.video_view);
        recyclerViewListBackground = findViewById(R.id.recyclerview_background);
        txtBackground = findViewById(R.id.textview_background);
        imgBackground = findViewById(R.id.imageview_background);


        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODEIMAGES && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            listImage.add(uri);
            arrayList = insertDataToFirebase.insertToFirebaseStorage(uri);
            setUpRecyclerView(listImage);
        }
        else if(requestCode == REQUESTCODEVIDEOS && resultCode == RESULT_OK && data != null){
                uri = data.getData();
                videoView.setVideoURI(uri);
        }
    }

    public void finsishActivity(View view) {
        finish();
    }

    //Ham thiet lap recycler view hien thi anh
    private void setUpRecyclerView(ArrayList<Uri> strings) {
        recyclerViewListImage.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(CreatePostActivity.this, 3);
        recyclerViewListImage.setLayoutManager(layoutManager);

        ListImageAdapter adapter = new ListImageAdapter(strings, CreatePostActivity.this);
        recyclerViewListImage.setAdapter(adapter);
    }

    //Ham thiet lap danh sach cac background
    private void setUpRecyclerViewBackground(){
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("background_post");

        recyclerViewListBackground.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CreatePostActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerViewListBackground.setLayoutManager(layoutManager);

        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                listBackground = new ArrayList<>();
                for(StorageReference item : listResult.getItems()){
                    item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String data = uri.toString();
                            listBackground.add(data);
                            adapter = new BackgroundAdapter(listBackground, CreatePostActivity.this);
                            recyclerViewListBackground.setAdapter(adapter);

                            adapter.setOnBackgroundListener(new BackgroundAdapter.OnBackgroundListener() {
                                @Override
                                public void onItemClick(int position) {
                                    imgBackground.setMaxHeight(300);
                                    background = listBackground.get(position);
                                    Glide.with(CreatePostActivity.this).load(background).into(imgBackground);
                                    edtInput.setGravity(Gravity.CENTER);
                                    edtInput.setTextSize(30);
                                    edtInput.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
