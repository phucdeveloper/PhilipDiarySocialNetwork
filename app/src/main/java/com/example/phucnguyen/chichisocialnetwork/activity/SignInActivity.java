package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.UserUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    Button btnSignIn;
    EditText edtEmail, edtPassword;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UserUtil.getIdUser(SignInActivity.this) != null) {
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            intent.putExtra("id", UserUtil.getIdUser(SignInActivity.this));
            startActivity(intent);
        }
        setContentView(R.layout.activity_sign_in);
        initView();

        Intent intent = getIntent();
        if (user != null) {
            user = (User) intent.getSerializableExtra("user");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignInActivity.this, "Bạn cần nhập đầy đủ thông tin để đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    signInApplication(email, password);
                }
            }
        });
    }

    private void signInApplication(String email, String password) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            FirebaseUser mUser = firebaseAuth.getCurrentUser();
                            if (mUser != null) {
                                String idUser = mUser.getUid();
                                databaseReference = firebaseDatabase.getReference().child("Users");
                                databaseReference.child(idUser).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        user = dataSnapshot.getValue(User.class);
                                        UserUtil.setIdUser(user.getUid(), SignInActivity.this);
                                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                        intent.putExtra("id", user.getUid());
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }

    public void moveToSignUpActivity(View view) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void initView() {
        btnSignIn = findViewById(R.id.button_sign_in);
        edtEmail = findViewById(R.id.edittext_input_email);
        edtPassword = findViewById(R.id.edittext_input_password);
    }
}
