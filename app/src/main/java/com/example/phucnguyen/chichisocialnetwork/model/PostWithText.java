package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
@IgnoreExtraProperties
public class PostWithText extends Post implements Serializable {

    private String contentPost; // Noi dung bai viet
    private String background;  // backgrounf bai viet
    public PostWithText(){
    }

    public PostWithText(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment, String contentPost) {
        super(idUser, idPost, timeCreate, numberFavorite, listComment);
        this.contentPost = contentPost;
    }

    public PostWithText(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment, String contentPost, String background) {
        super(idUser, idPost, timeCreate, numberFavorite, listComment);
        this.contentPost = contentPost;
        this.background = background;
    }

    public String getContentPost() {
        return contentPost;
    }

    public void setContentPost(String contentPost) {
        this.contentPost = contentPost;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackground(){
        return background;
    }
}
