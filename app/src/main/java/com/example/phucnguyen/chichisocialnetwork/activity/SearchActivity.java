package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerViewListKeyWord;
    AutoCompleteTextView autoCompleteTextView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<User> arrayList = new ArrayList<>();
    ArrayList<String> listResult = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerViewListKeyWord = findViewById(R.id.recyclerview_list_key_word);
        autoCompleteTextView = findViewById(R.id.autocompletetextview_search);

        firebaseDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        if(user != null){
            databaseReference = firebaseDatabase.getReference().child("Users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data: dataSnapshot.getChildren()){
                        User dataUser = data.getValue(User.class);
                        arrayList.add(dataUser);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            for (int i = 0; i < arrayList.size(); i++){
                listResult.add(arrayList.get(i).getNickname());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, listResult);
            autoCompleteTextView.setAdapter(adapter);
        }
    }
}
