package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentFriend;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentHome;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentMenu;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentNotification;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentWatch;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.callback.OnClickListener;
import com.example.phucnguyen.chichisocialnetwork.utils.UserUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements OnClickListener {

    BottomNavigationView bottomNavigationView;

    User user;
    String idUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        Intent intent = getIntent();
        if (intent != null){
            idUser = intent.getStringExtra("id");
        }
        else{
            idUser = UserUtil.getIdUser(HomeActivity.this);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbUser = firebaseDatabase.getReference().child("Users");
        dbUser.child(idUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);

                bottomNavigationView.setOnNavigationItemSelectedListener(listener);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentHome(user)).commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new FragmentHome(user);
                    break;
                case R.id.watch:
                    fragment = new FragmentWatch();
                    break;
                case R.id.group:
                    fragment = new FragmentFriend(user);
                    break;
                case R.id.notification:
                    fragment = new FragmentNotification();
                    break;
                case R.id.menu:
                    fragment = new FragmentMenu(user);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };

    @Override
    public void onClick(User user, int code) {
        Intent intent = new Intent(HomeActivity.this, SuggestionActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onClick(String idUser) {
        //Nhan interface tu fragment menu va di chuyen den man hinh account
        Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
        intent.putExtra("UserID", idUser);
        startActivity(intent);
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view_menu);
    }
}
