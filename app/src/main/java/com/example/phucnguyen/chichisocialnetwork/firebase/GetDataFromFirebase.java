package com.example.phucnguyen.chichisocialnetwork.firebase;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.example.phucnguyen.chichisocialnetwork.model.Comment;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class GetDataFromFirebase {

    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    public GetDataFromFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public ArrayList<Timeline> getListPostFromFirebaseDatabase(){
        final ArrayList<Timeline> arrayList = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("Post");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Timeline timeline = data.getValue(Timeline.class);
                    arrayList.add(timeline);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return arrayList;
    }

    public ArrayList<Comment> getListCommentFromFirebaseDatabase(String idPost){
        final ArrayList<Comment> arrayList = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("Comments").child(idPost);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Comment comment = data.getValue(Comment.class);
                    arrayList.add(comment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return arrayList;
    }

    public ArrayList<User> getListUserFromFirebaseDatabase(){
        final ArrayList<User> arrayList = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    User user = data.getValue(User.class);
                    arrayList.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return arrayList;
    }
}
