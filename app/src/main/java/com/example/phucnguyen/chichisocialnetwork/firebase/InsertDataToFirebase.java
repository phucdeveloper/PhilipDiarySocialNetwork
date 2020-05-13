package com.example.phucnguyen.chichisocialnetwork.firebase;

import android.net.Uri;

import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class InsertDataToFirebase {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private ArrayList<String> arrayList = new ArrayList<>();


    public InsertDataToFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    //Ham insert bai viet voi chu vao firebase database
    public void insertPostWithTextToFirebaseDatabae(String idUser, String key, Timeline timeline) {
        databaseReference = firebaseDatabase.getReference().child("Posts");
        databaseReference.child(idUser).child(key).setValue(timeline);
    }

    //Ham insert bai viet voi anh vao firebase database
    public void insertPostWithImageToFirebaseDatabase(String idUser, String key, Timeline timeline) {
        databaseReference = firebaseDatabase.getReference().child("Posts");
        databaseReference.child(idUser).child(key).setValue(timeline);
    }

    //Ham dua anh vao trong firebase storage va lay link anh
    public ArrayList<String> insertToFirebaseStorage(Uri uri) {
        storageReference = firebaseStorage.getReference().child("image_post");
        final StorageReference imageFilePath = storageReference.child(uri.getLastPathSegment());
        imageFilePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String linkImage = uri.toString();
                        arrayList.add(linkImage);
                    }
                });
            }
        });

        return arrayList;
    }

    public void insertUserToFirebaseDatabase(String idUser, User user){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        databaseReference.child(idUser).setValue(user);
    }
}
