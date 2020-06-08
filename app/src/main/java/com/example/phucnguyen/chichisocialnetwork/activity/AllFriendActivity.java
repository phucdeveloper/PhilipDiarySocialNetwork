package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.ListFriendAdapter;
import com.example.phucnguyen.chichisocialnetwork.model.Friend;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;
import com.example.phucnguyen.chichisocialnetwork.utils.UserUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllFriendActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewListAllFriend;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataRef;

    ArrayList<Friend> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friend);
        initView();

        String idUser = UserUtil.getIdUser(AllFriendActivity.this);

        setUpRecyclerView();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRef = firebaseDatabase.getReference().child("Friend");
        dataRef.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Friend friend = snapshot.getValue(Friend.class);
                    arrayList.add(friend);
                }

                ListFriendAdapter adapter = new ListFriendAdapter(arrayList, AllFriendActivity.this, Constant.ITEM_FRIEND_IN_ALL_FRIEND);
                recyclerViewListAllFriend.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void initView(){
        toolbar = findViewById(R.id.toolbar_all_friend);
        recyclerViewListAllFriend = findViewById(R.id.recyclerview_list_all_friend);
    }

    private void setUpRecyclerView(){
        recyclerViewListAllFriend.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AllFriendActivity.this, RecyclerView.VERTICAL, false);
        recyclerViewListAllFriend.setLayoutManager(layoutManager);

    }
}
