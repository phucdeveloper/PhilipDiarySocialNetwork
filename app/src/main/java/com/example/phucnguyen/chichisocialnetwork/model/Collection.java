package com.example.phucnguyen.chichisocialnetwork.model;

public class Collection {
    private String nameCollection;
    private String linkImage;
    private String nameAccount;
    private String describe;
    private String timeCreate;


    public Collection(String nameCollection, String linkImage, String nameAccount, String timeCreate) {
        this.nameCollection = nameCollection;
        this.linkImage = linkImage;
        this.nameAccount = nameAccount;
        this.timeCreate = timeCreate;
    }

    public Collection(String nameCollection, String linkImage, String nameAccount, String describe, String timeCreate) {
        this.nameCollection = nameCollection;
        this.linkImage = linkImage;
        this.nameAccount = nameAccount;
        this.describe = describe;
        this.timeCreate = timeCreate;
    }

    public String getNameCollection() {
        return nameCollection;
    }

    public void setNameCollection(String nameCollection) {
        this.nameCollection = nameCollection;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }
}
