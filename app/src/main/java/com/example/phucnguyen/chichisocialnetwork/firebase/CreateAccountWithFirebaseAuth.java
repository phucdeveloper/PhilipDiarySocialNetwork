package com.example.phucnguyen.chichisocialnetwork.firebase;

import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountWithFirebaseAuth {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private InsertDataToFirebase insertDataToFirebase = new InsertDataToFirebase();

    public void createAccount(final String email, final String password) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if(authResult.getUser() != null){
                    currentUser = authResult.getUser();
                    String idUser = currentUser.getUid();
                    User user = new User(idUser, email, password, null, null, null, null, null, null, null, null, null, null);
                    insertDataToFirebase.insertUserToFirebaseDatabase(idUser, user);
                }
            }
        });
    }
}
