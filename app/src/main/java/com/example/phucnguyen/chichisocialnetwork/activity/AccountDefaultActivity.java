package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;

public class AccountDefaultActivity extends AppCompatActivity {

    ImageView imgAvatar, imgBackground;
    EditText edtSearch;
    TextView txtNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_default);

        initView();

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("default_user");
        if(user != null){
            edtSearch.setText(user.getNickname());
            txtNickname.setText(user.getNickname());
            Glide.with(AccountDefaultActivity.this).load(user.getAvatar()).into(imgAvatar);
            Glide.with(AccountDefaultActivity.this).load(user.getBackground()).into(imgBackground);
        }
    }

    private void initView(){
        imgAvatar = findViewById(R.id.imageview_avatar);
        imgBackground = findViewById(R.id.imageview_background);
        edtSearch = findViewById(R.id.edittext_search);
        txtNickname = findViewById(R.id.textview_text_nickname);
    }
}
