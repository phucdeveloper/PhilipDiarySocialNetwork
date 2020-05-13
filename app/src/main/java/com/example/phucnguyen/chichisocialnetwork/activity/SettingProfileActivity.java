package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;

public class SettingProfileActivity extends AppCompatActivity {

    LinearLayout layoutEditProfile;
    Toolbar toolbar;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);
        initView();

        Intent intent = getIntent();
        if(intent != null){
            user = (User) intent.getSerializableExtra("user");
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        layoutEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        layoutEditProfile = findViewById(R.id.layout_edit_profile);
        toolbar = findViewById(R.id.toolbar_setting_profile);
    }
}
