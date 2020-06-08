package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.AllFriendActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.SearchActivity;
import com.example.phucnguyen.chichisocialnetwork.adapter.InviteFriendAdapter;
import com.example.phucnguyen.chichisocialnetwork.callback.OnClickListener;
import com.example.phucnguyen.chichisocialnetwork.model.Friend;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentFriend extends Fragment {

    RecyclerView recyclerViewListInviteFriend;
    Button btnSuggestion, btnAllFriend;
    ImageButton imgButtonSearch;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataRefInviteFriend, dataRefFriend;

    User user;
    ArrayList<User> arrayList;
    Friend friend;

    public FragmentFriend(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        imgButtonSearch = view.findViewById(R.id.imagebutton_search);
        btnSuggestion = view.findViewById(R.id.button_suggestion);
        btnAllFriend = view.findViewById(R.id.button_all_friend);
        recyclerViewListInviteFriend = view.findViewById(R.id.recyclerview_list_invite_friend);
        recyclerViewListInviteFriend.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRefInviteFriend = firebaseDatabase.getReference().child("InviteFriend");
        dataRefInviteFriend.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    arrayList.add(user);
                }

                InviteFriendAdapter adapter = new InviteFriendAdapter(arrayList, getContext(), user);
                recyclerViewListInviteFriend.setAdapter(adapter);

                adapter.setOnAcceptListener(new InviteFriendAdapter.OnAcceptInviteFriendClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        dataRefFriend = firebaseDatabase.getReference().child("Friend");
                        Toast.makeText(getContext(), "Ok, hai bạn đã là bạn bè của nhau", Toast.LENGTH_SHORT).show();
                        String idFriend = String.valueOf(System.currentTimeMillis());
                        friend = new Friend(idFriend, user.getUid(), arrayList.get(position).getUid());
                        dataRefFriend.child(user.getUid()).child(idFriend).setValue(friend);

                        dataRefInviteFriend.child(user.getUid()).child(arrayList.get(position).getUid()).removeValue();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnAllFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllFriendActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewListInviteFriend.setLayoutManager(layoutManager);

        btnSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickListener onClickListener = (OnClickListener) getContext();
                if (onClickListener != null) {
                    onClickListener.onClick(user, 123);
                }
            }
        });

        imgButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}
