package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.CreatePostActivity;
import com.example.phucnguyen.chichisocialnetwork.adapter.NewPostAdapter;
import com.example.phucnguyen.chichisocialnetwork.adapter.PostAdapter;
import com.example.phucnguyen.chichisocialnetwork.callback.OnLikeClickListener;
import com.example.phucnguyen.chichisocialnetwork.model.NewPost;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements OnLikeClickListener {
    RecyclerView recyclerViewListNewPost, recyclerViewListPost;
    Button btnCreatePost;
    ImageView imgAvatar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    User user;
    PostAdapter adapter1;
    ArrayList<Timeline> listPost;

    public FragmentHome(User user){
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewListNewPost = view.findViewById(R.id.recyclerview_list_new_post);
        btnCreatePost = view.findViewById(R.id.button_add_post);
        imgAvatar = view.findViewById(R.id.imageview_avatar);
        recyclerViewListPost = view.findViewById(R.id.recyclerview_list_post);

        if(user != null){
            Glide.with(this).load(user.getAvatar()).into(imgAvatar);
        }

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        setUpRecyclerViewListPost();

        return view;
    }

    private void setUpRecyclerViewListNewPost(){
        //Thiet lap recyclerview cho ban tin cua user nay
        recyclerViewListNewPost.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewListNewPost.setLayoutManager(layoutManager);

        ArrayList<NewPost> arrayList = new ArrayList<>();
        arrayList.add(new NewPost(user.getNickname(), user.getAvatar(), user.getAvatar(), 1));

        NewPostAdapter adapter = new NewPostAdapter(arrayList, getContext());
        recyclerViewListNewPost.setAdapter(adapter);
    }

    private void setUpRecyclerViewListPost(){
        recyclerViewListPost.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewListPost.setLayoutManager(layoutManager1);

        listPost = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Lay cac bai viet cua user nay va hien thi len recyclerview
        databaseReference = firebaseDatabase.getReference().child("Posts").child(user.getUid());
        databaseReference.orderByChild("timeCreate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Timeline timeline = data.getValue(Timeline.class);
                   // listPost.clear();
                    listPost.add(timeline);
                }

                adapter1 = new PostAdapter(listPost, getContext(), user);
                recyclerViewListPost.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        setUpRecyclerViewListNewPost();
    }

    @Override
    public void onLikeClickItem(int position) {
        adapter1.notifyDataSetChanged();
    }
}
