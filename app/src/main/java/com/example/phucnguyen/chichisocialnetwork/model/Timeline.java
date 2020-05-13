package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Timeline implements Serializable {
    private PostWithImage postImage, postImageShared;
    private PostWithText postText, postTextShared;
    private PostWithVideo postVideo;
    private int viewType;

    public Timeline(){}

    public Timeline(PostWithImage postImage, int viewType) {
        this.postImage = postImage;
        this.viewType = viewType;
    }

    public Timeline(PostWithText postText, int viewType) {
        this.postText = postText;
        this.viewType = viewType;
    }

    public Timeline(PostWithVideo postVideo, int viewType){
        this.postVideo = postVideo;
        this.viewType = viewType;
    }

    public Timeline(PostWithText postText, PostWithImage postImageShared, int viewType) {
        this.postText = postText;
        this.postImageShared = postImageShared;
        this.viewType = viewType;
    }

    public Timeline(PostWithText postText, PostWithText postTextShared, int viewType) {
        this.postText = postText;
        this.postTextShared = postTextShared;
        this.viewType = viewType;
    }

    public PostWithImage getPostImage() {
        return postImage;
    }

    public PostWithText getPostText() {
        return postText;
    }

    public int getViewType() {
        return viewType;
    }

    public PostWithText getPostTextShared() {
        return postTextShared;
    }

    public PostWithImage getPostImageShared() {
        return postImageShared;
    }

    public void setPostImageShared(PostWithImage postImageShared) {
        this.postImageShared = postImageShared;
    }

    public void setPostVideo(PostWithVideo postVideo) {
        this.postVideo = postVideo;
    }

    public PostWithVideo getPostVideo() {
        return postVideo;
    }
}
