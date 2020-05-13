package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.SuggestionFriendAdapter;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    RecyclerView recyclerViewListFriendSuggestion;
    Toolbar toolbar;

    User user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        initView();

        Intent intent = getIntent();

        if (intent != null){
            user = (User) intent.getSerializableExtra("user");
        }

        setUpRecyclerView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpRecyclerView(){
        final ArrayList<User> arrayList = new ArrayList<>();
        recyclerViewListFriendSuggestion.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SuggestionActivity.this, RecyclerView.VERTICAL, false);
        recyclerViewListFriendSuggestion.setLayoutManager(layoutManager);

        if (user != null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("Users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        User dataUser = data.getValue(User.class);
                        if (dataUser != null) {
                            if (!dataUser.getUid().equals(user.getUid())) {
                                arrayList.add(dataUser);
                            }
                        }
                    }
                    final SuggestionFriendAdapter adapter = new SuggestionFriendAdapter(arrayList, SuggestionActivity.this, user);
                    recyclerViewListFriendSuggestion.setAdapter(adapter);

                    adapter.setOnInviteFriendClickListener(new SuggestionFriendAdapter.OnInviteFriendClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            String idFriend = arrayList.get(position).getUid();
                            User newUser = new User(user.getUid(), user.getNickname(), user.getAvatar());
                            databaseReference = firebaseDatabase.getReference().child("InviteFriend");
                            databaseReference.child(idFriend).child(user.getUid()).setValue(newUser);
                            arrayList.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void initView(){
        recyclerViewListFriendSuggestion = findViewById(R.id.recyclerview_list_friend_suggestion);
        toolbar = findViewById(R.id.toolbar_suggestion);
    }
}
