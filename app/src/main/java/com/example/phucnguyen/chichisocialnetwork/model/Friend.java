package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Friend {
    private String idFriend;
    private String idUser1;
    private String idUser2;

    public Friend(){}

    public Friend(String idFriend, String idUser1, String idUser2){
        this.idFriend = idFriend;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
    }

    public String getIdFriend() {
        return idFriend;
    }

    public String getIdUser1() {
        return idUser1;
    }

    public String getIdUser2() {
        return idUser2;
    }
}
