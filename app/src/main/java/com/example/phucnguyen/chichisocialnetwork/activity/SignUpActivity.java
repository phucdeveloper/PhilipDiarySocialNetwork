package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentBirthDay;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentComplete;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentEmailPassword;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentName;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentSex;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.callback.OnSendDataClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity implements OnSendDataClickListener, FragmentComplete.OnSignUpClickListener {

    ViewPager viewPager;
    String dataEmail, dataPassword, dataName, dataSex, dataBirthDay, idUser;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        viewPager = findViewById(R.id.view_pager_container);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
    }

    public void setPositionViewPager(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onSendDataName(String name) {
        dataName = name;
    }

    @Override
    public void onSendDataEmail(String email, String password) {
        dataEmail = email;
        dataPassword = password;
    }

    @Override
    public void onSendDataSex(String sex) {
        dataSex = sex;
    }

    @Override
    public void onSendDataBirthDay(String birthday) {
        dataBirthDay = birthday;
    }

    @Override
    public void onSuccess(boolean isClick) {
        registerApplication(dataEmail, dataPassword);
    }

    private void registerApplication(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
                            idUser = firebaseAuth.getCurrentUser().getUid();
                            Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            User user = new User(idUser, dataEmail, dataPassword, dataName, "avatar", "background", dataSex, dataBirthDay, "address",
                                    "country", "work", "maritalStatus", "literacy");
                            databaseReference = firebaseDatabase.getReference().child("Users");
                            databaseReference.child(idUser).setValue(user);
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                        }
                    }
                });
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentEmailPassword();
                case 1:
                    return new FragmentName();
                case 2:
                    return new FragmentSex();
                case 3:
                    return new FragmentBirthDay();
                case 4:
                    return new FragmentComplete();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
