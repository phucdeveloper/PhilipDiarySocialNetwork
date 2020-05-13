package com.example.phucnguyen.chichisocialnetwork.model;

public class NewPost {
    private String name;
    private String avatar;
    private String image;
    private int typeNewPost;

    public NewPost(String name, String avatar, String image, int typeNewPost) {
        this.name = name;
        this.avatar = avatar;
        this.image = image;
        this.typeNewPost = typeNewPost;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getImage() {
        return image;
    }

    public int getTypeNewPost() {
        return typeNewPost;
    }
}
