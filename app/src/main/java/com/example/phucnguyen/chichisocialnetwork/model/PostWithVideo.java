package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

@IgnoreExtraProperties
public class PostWithVideo extends Post implements Serializable {

    private String statusLine;
    private String urlVideo;

    public PostWithVideo(){}

    public PostWithVideo(String idUser, String idPost, String timeCreate, double numberFavorite, ArrayList<Comment> listComment, String statusLine, String urlVideo) {
        super(idUser, idPost, timeCreate, numberFavorite, listComment);
        this.statusLine = statusLine;
        this.urlVideo = urlVideo;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlVideo(){
        return urlVideo;
    }
}
