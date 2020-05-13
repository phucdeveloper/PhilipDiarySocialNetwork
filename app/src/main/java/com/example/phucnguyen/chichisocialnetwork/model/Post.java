package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
@IgnoreExtraProperties
public class Post implements Serializable {
    private String idUser; //Id cua nguoi dang bai viet
    private String idPost; //Id cua bai viet
    private String timeCreate;   // Thoi gian tao ra bai viet
    private double numberFavorite;  // So luong nguoi thich bai viet
    private ArrayList<Comment> listComment;  // So luong binh luan trong bai viet

    public Post(){}

    public Post(String idPost, String nameAccount, String timeCreate, String avatarAccount, double numberFavorite) {
        this.idPost = idPost;
        this.timeCreate = timeCreate;
        this.numberFavorite = numberFavorite;
    }

    public Post(String idPost, String timeCreate) {
        this.idPost = idPost;
        this.timeCreate = timeCreate;
    }

    public Post(String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment) {
        this.idPost = idPost;
        this.timeCreate = timeCreate;
        this.numberFavorite = numberFavorite;
        this.listComment = listComment;
    }

    public Post(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.timeCreate = timeCreate;
        this.numberFavorite = numberFavorite;
        this.listComment = listComment;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public double getNumberFavorite() {
        return numberFavorite;
    }

    public void setNumberFavorite(double numberFavorite) {
        this.numberFavorite = numberFavorite;
    }

    public ArrayList<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(ArrayList<Comment> listComment) {
        this.listComment = listComment;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
}
