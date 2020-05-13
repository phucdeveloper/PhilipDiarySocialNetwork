package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.firebase.InsertDataToFirebase;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithImage;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithText;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class PostShareActivity extends AppCompatActivity {

    ImageView imgAvatarPostShared, imgAvatar;
    TextView txtCreatePost, txtContentPostShared, txtNicknamePostShared, txtTimePostShared, txtNickname;
    EditText editText;
    ViewPager viewPager;
    LinearLayout layoutPostTextShared, layoutPostImageShared;

    InsertDataToFirebase insertDataToFirebase = new InsertDataToFirebase();
    PostWithText postText, postTextShared;
    PostWithImage  postImageShared;
    Timeline timeline;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_share);

        initView();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle != null){
            timeline = (Timeline) bundle.getSerializable("timeline");
            user = (User) bundle.getSerializable("user");
        }

        Glide.with(PostShareActivity.this).load(user.getAvatar()).into(imgAvatar);
        txtNickname.setText(user.getNickname());

    /*    if (timeline != null) {
            switch (timeline.getViewType()) {
                case Constant.ITEM_POST_WITH_TEXT:
                    layoutPostImageShared.setVisibility(View.INVISIBLE);
                    layoutPostTextShared.setVisibility(View.VISIBLE);
                    txtContentPostShared.setText(timeline.getPostText().getContentPost());
                    Glide.with(PostShareActivity.this).load(user.getAvatar()).into(imgAvatarPostShared);
                    txtNicknamePostShared.setText(user.getNickname());
                    txtTimePostShared.setText(timeline.getPostText().getTimeCreate());
                    postTextShared = timeline.getPostText();
                    break;
                case Constant.ITEM_POST_WITH_IMAGE:
                    layoutPostTextShared.setVisibility(View.INVISIBLE);
                    layoutPostImageShared.setVisibility(View.VISIBLE);
                    Glide.with(PostShareActivity.this).load(user.getAvatar()).into(imgAvatarPostShared);
                    txtNicknamePostShared.setText(user.getNickname());
                    txtTimePostShared.setText(timeline.getPostImage().getTimeCreate());
                    ArrayList<String> arrayList = timeline.getPostImage().getArrayList();
                    ImagePagerAdapter adapter = new ImagePagerAdapter(arrayList, PostShareActivity.this);
                    viewPager.setAdapter(adapter);
                    postImageShared = timeline.getPostImage();
                    break;
            }
        }*/

        txtCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeCreate = DateFormat.getDateTimeInstance().format(new Date());
                String idPost = UUID.randomUUID().toString();
                String contentPost = editText.getText().toString();

                if(timeline.getViewType() == Constant.ITEM_POST_WITH_TEXT){
                    postText = new PostWithText(user.getUid(), idPost, timeCreate,
                            0, null, contentPost);
                    Timeline timeline = new Timeline(postText, postTextShared, Constant.ITEM_POST_TEXT_SHARED);
                    insertDataToFirebase.insertPostWithTextToFirebaseDatabae(user.getUid(), idPost, timeline);
                }

                if(timeline.getViewType() == Constant.ITEM_POST_WITH_IMAGE){
                    postText = new PostWithText(user.getUid(), idPost, timeCreate,
                            0, null, contentPost);
                    Timeline timeline = new Timeline(postText, postImageShared, Constant.ITEM_POST_IMAGE_SHARED);
                    insertDataToFirebase.insertPostWithTextToFirebaseDatabae(user.getUid(), idPost, timeline);
                }
            }
        });

    }

    public void comeBackMainActivity(View view) {
        finish();
    }

    private void initView() {
        imgAvatar = findViewById(R.id.imageview_avatar_share);
        txtNickname = findViewById(R.id.textview_nickname_share);
        txtCreatePost = findViewById(R.id.textview_create_post);
        editText = findViewById(R.id.edittext_input);

        imgAvatarPostShared = findViewById(R.id.imageview_avatar_post_shared);
        txtContentPostShared = findViewById(R.id.textview_content_post_shared);
        txtNicknamePostShared = findViewById(R.id.textview_nickname_post_shared);
        txtTimePostShared = findViewById(R.id.textview_time_post_shared);
        viewPager = findViewById(R.id.view_pager);
        layoutPostTextShared = findViewById(R.id.layout_post_text_shared);
        layoutPostImageShared = findViewById(R.id.layout_post_image_shared);
    }
}
