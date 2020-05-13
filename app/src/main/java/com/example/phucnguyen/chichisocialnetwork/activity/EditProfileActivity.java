package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    ImageView imgAvatar, imgBackground;
    TextView txtEdit, txtWork, txtStudent, txtCountry, txtAddress;
    EditText edtSlogan;
    Button btnUpdateSlogan;
    LinearLayout layout;

    User user;
    String slogan;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initView();

        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            user = (User) intent.getSerializableExtra("user");
        }

        if(user != null){
            Glide.with(EditProfileActivity.this).load(user.getAvatar()).into(imgAvatar);
            Glide.with(EditProfileActivity.this).load(user.getBackground()).into(imgBackground);
            txtAddress.setText(user.getAddress());
            txtCountry.setText(user.getCountry());
            txtStudent.setText(user.getLiteracy());
            txtWork.setText(user.getWork());
        }



        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slogan = edtSlogan.getText().toString();
                if(slogan != null){
                    edtSlogan.requestFocus();
                    edtSlogan.setSelection(slogan.length());
                    btnUpdateSlogan.setEnabled(true);
                }
                else{
                    edtSlogan.requestFocus();
                    edtSlogan.setSelection(0);
                    btnUpdateSlogan.setEnabled(true);
                }
            }
        });

        btnUpdateSlogan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInforUser(user.getUid(), user.getSlogan(), slogan);
            }
        });
    }

    public void onComeBackClick(View view){
        finish();
    }

    private void initView() {
        imgAvatar = findViewById(R.id.imageview_avatar);
        imgBackground = findViewById(R.id.imageview_background);
        edtSlogan = findViewById(R.id.edittext_slogan);
        txtEdit = findViewById(R.id.textview_edit);
        btnUpdateSlogan = findViewById(R.id.button_update_slogan);
        txtWork = findViewById(R.id.textview_work);
        txtAddress = findViewById(R.id.textview_address);
        txtCountry = findViewById(R.id.textview_country);
        txtStudent = findViewById(R.id.textview_student);
        layout = findViewById(R.id.layout_detail);
    }

    private void updateInforUser(String idUser, String key, String value){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(idUser).child(key).setValue(value);
    }
}
