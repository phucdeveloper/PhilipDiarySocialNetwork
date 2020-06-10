package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.AddMemberAdapter;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentAddMember;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentTypeGroup;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.UserUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateGroupActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnCreateGroup;
    EditText edtNameGroup;
    RadioButton rbPublic, rbPrivate;
    TextView txtAddImageBackground;
    ImageView imgBackground;


    static int REQUESTCODE = 123;
    boolean isPublic = false;
    String avatar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataRef;
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
                showDialog();
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

    private void showDialog(){
        Button btnCancel, btnComplete;
        final RecyclerView recyclerViewListMember;

        final ArrayList<User> arrayList =  new ArrayList<>();
        final Dialog dialog = new Dialog(CreateGroupActivity.this);
        dialog.setContentView(R.layout.layout_dialog_add_member);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
        btnCancel = dialog.findViewById(R.id.button_cancel);
        btnComplete = dialog.findViewById(R.id.button_complete);
        recyclerViewListMember = dialog.findViewById(R.id.recyclerview_list_member);
        recyclerViewListMember.setHasFixedSize(true);

        recyclerViewListMember.setLayoutManager(new LinearLayoutManager(CreateGroupActivity.this));

        String idUser = UserUtil.getIdUser(CreateGroupActivity.this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRef = firebaseDatabase.getReference().child("Friend");
        dataRef.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    arrayList.add(user);
                }

                AddMemberAdapter adapter = new AddMemberAdapter(arrayList, CreateGroupActivity.this);
                recyclerViewListMember.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreateGroupActivity.this, "Group Created", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


  /*  private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupActivity.this);
        builder.setView(R.layout.layout_dialog_add_member);
        final AlertDialog dialog = builder.create();
        viewPager = dialog.findViewById(R.id.container);
        tabLayout = dialog.findViewById(R.id.tab_layout);
        btnCancel = dialog.findViewById(R.id.button_cancel);
        btnComplete = dialog.findViewById(R.id.button_complete);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

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
        txtAddImageBackground = findViewById(R.id.textview_add_image_background);
        imgBackground = findViewById(R.id.imageview_background_group);

    }
}
