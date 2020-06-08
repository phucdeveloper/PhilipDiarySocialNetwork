package com.example.phucnguyen.chichisocialnetwork.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Friend implements Parcelable {
    private String idFriend;
    private String idUser1;
    private String idUser2;

    public Friend(){}

    public Friend(String idFriend, String idUser1, String idUser2){
        this.idFriend = idFriend;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
    }

    protected Friend(Parcel in) {
        idFriend = in.readString();
        idUser1 = in.readString();
        idUser2 = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public String getIdFriend() {
        return idFriend;
    }

    public String getIdUser1() {
        return idUser1;
    }

    public String getIdUser2() {
        return idUser2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idFriend);
        parcel.writeString(idUser1);
        parcel.writeString(idUser2);
    }
}
