package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {
    private String idComment;
    private String nameAccount; //Ten nguoi binh luan bai viet
    private String avatarAccount;  //Avatar ten nguoi binh luan bai viet
    private String contentComment;  //Noi dung binh luan (co the la image hoac text)
    private int numberFavorite;
    private int viewType;

    public Comment(){}

    public Comment(String idComment, String nameAccount, String avatarAccount, String contentComment, int numberFavorite, int viewType) {
        this.idComment = idComment;
        this.nameAccount = nameAccount;
        this.avatarAccount = avatarAccount;
        this.contentComment = contentComment;
        this.numberFavorite = numberFavorite;
        this.viewType = viewType;
    }

    public Comment(String nameAccount, String avatarAccount, String contentComment, int numberFavorite, int viewType) {
        this.nameAccount = nameAccount;
        this.avatarAccount = avatarAccount;
        this.contentComment = contentComment;
        this.numberFavorite = numberFavorite;
        this.viewType = viewType;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getAvatarAccount() {
        return avatarAccount;
    }

    public void setAvatarAccount(String avatarAccount) {
        this.avatarAccount = avatarAccount;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public int getNumberFavorite() {
        return numberFavorite;
    }

    public void setNumberFavorite(int numberFavorite) {
        this.numberFavorite = numberFavorite;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getIdComment() {
        return idComment;
    }
}
