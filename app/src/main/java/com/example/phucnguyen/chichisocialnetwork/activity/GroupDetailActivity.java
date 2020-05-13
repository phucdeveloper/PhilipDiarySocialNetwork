package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Group;

import cn.yiiguxing.compositionavatar.CompositionAvatarView;

public class GroupDetailActivity extends AppCompatActivity {

    ImageView imgBackgroundGroup;
    TextView txtNameGroup;
    CompositionAvatarView compositionAvatarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        initView();

        Intent intent = getIntent();
        Group group = (Group) intent.getSerializableExtra("group");

        if (group != null) {
            imgBackgroundGroup.setImageResource(group.getAvatarGroup());
            txtNameGroup.setText(group.getNameGroup());
        }
    }

    private void initView() {
        imgBackgroundGroup = findViewById(R.id.imageview_background_group);
        txtNameGroup = findViewById(R.id.textview_name_group);
        compositionAvatarView = findViewById(R.id.composition_avatar_view);
    }
}
