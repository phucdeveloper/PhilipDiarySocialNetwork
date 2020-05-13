package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

@IgnoreExtraProperties
public class PostWithImage extends Post implements Serializable {

    private String statusLine;
    private String image;
    private ArrayList<String> arrayList;

    public PostWithImage(){}

    public PostWithImage(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment, String statusLine, ArrayList<String> arrayList) {
        super(idUser, idPost, timeCreate, numberFavorite, listComment);
        this.statusLine = statusLine;
        this.arrayList = arrayList;
    }

    public PostWithImage(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment, ArrayList<String> arrayList) {
        super(idUser, idPost, timeCreate, numberFavorite, listComment);
        this.arrayList = arrayList;
    }

    public PostWithImage(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment, String statusLine, String image) {
        super(idUser, idPost, timeCreate, numberFavorite, listComment);
        this.statusLine = statusLine;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }
}
