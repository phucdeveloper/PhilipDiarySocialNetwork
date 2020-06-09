package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentAddMember;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentTypeGroup;
import com.example.phucnguyen.chichisocialnetwork.model.Group;
import com.google.android.material.tabs.TabLayout;

public class CreateGroupActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnCreateGroup;
    EditText edtNameGroup;
    RadioButton rbPublic, rbPrivate;
    TextView txtAddImageBackground;
    ImageView imgBackground;
    ViewPager viewPager;
    TabLayout tabLayout;


    static int REQUESTCODE = 123;
    boolean isPublic = false;
    String avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtAddImageBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        rbPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPublic = true;
            }
        });

        rbPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPublic = false;
            }
        });

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLog();
         /*       if(isPublic){
                    String publicGroup = rbPublic.getText().toString();
                    String nameGroup = edtNameGroup.getText().toString();
                    Group group = new Group(nameGroup, avatar, publicGroup);
                }
                else{
                    String privateGroup = rbPrivate.getText().toString();
                    String nameGroup = edtNameGroup.getText().toString();
                    Group group = new Group(nameGroup, avatar, privateGroup);
                }*/

            }
        });

    }

    class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FragmentAddMember();
                case 1:
                    return new FragmentTypeGroup();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void showDiaLog(){
        Dialog dialog = new Dialog(CreateGroupActivity.this);
        dialog.setContentView(R.layout.layout_dialog_create_group);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager = dialog.findViewById(R.id.container);
        tabLayout = dialog.findViewById(R.id.tab_layout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        dialog.show();
    }



   /* private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupActivity.this);
        builder.setView(R.layout.layout_dialog_create_group);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        AlertDialog dialog = builder.create();
        viewPager = dialog.findViewById(R.id.container);
        tabLayout = dialog.findViewById(R.id.tab_layout);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        dialog.show();
    }*/

    private void openGallery(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){
            Uri uri = data.getData();
            avatar = uri.toString();
            Glide.with(CreateGroupActivity.this).load(avatar).override(250, 250).into(imgBackground);
            txtAddImageBackground.setText("Thay đổi ảnh bìa");
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_create_group);
        btnCreateGroup = findViewById(R.id.button_create_group);
        edtNameGroup = findViewById(R.id.edittext_name_group);
        rbPublic = findViewById(R.id.radiobutton_public);
        rbPrivate = findViewById(R.id.radiobutton_private);
        btnCreateGroup.setEnabled(false);
        txtAddImageBackground = findViewById(R.id.textview_add_image_background);
        imgBackground = findViewById(R.id.imageview_background_group);

    }
}
