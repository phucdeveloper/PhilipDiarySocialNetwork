package com.example.phucnguyen.chichisocialnetwork.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private String nameGroup;
    private User adminGroup;
    private String avatarGroup;
    private ArrayList<User> listUsers;
    private ArrayList<Timeline> listTimelines;
    private String typeGroup;
    private String modeGroup;

      public Group(String nameGroup, String avatarGroup, String typeGroup) {
        this.nameGroup = nameGroup;
        this.avatarGroup = avatarGroup;
        this.typeGroup = typeGroup;
    }

    public Group(String nameGroup, String avatarGroup, String typeGroup, String modeGroup) {
        this.nameGroup = nameGroup;
        this.avatarGroup = avatarGroup;
        this.typeGroup = typeGroup;
        this.modeGroup = modeGroup;
    }

    public Group(String nameGroup, User adminGroup, String avatarGroup, ArrayList<User> listUsers, ArrayList<Timeline> listTimelines, String typeGroup, String modeGroup) {
        this.nameGroup = nameGroup;
        this.adminGroup = adminGroup;
        this.avatarGroup = avatarGroup;
        this.listUsers = listUsers;
        this.listTimelines = listTimelines;
        this.typeGroup = typeGroup;
        this.modeGroup = modeGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public User getAdminGroup() {
        return adminGroup;
    }

    public void setAdminGroup(User adminGroup) {
        this.adminGroup = adminGroup;
    }

    public String getAvatarGroup() {
        return avatarGroup;
    }

    public void setAvatarGroup(String avatarGroup) {
        this.avatarGroup = avatarGroup;
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }

    public ArrayList<Timeline> getListTimelines() {
        return listTimelines;
    }

    public void setListTimelines(ArrayList<Timeline> listTimelines) {
        this.listTimelines = listTimelines;
    }

    public String getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(String typeGroup) {
        this.typeGroup = typeGroup;
    }

    public String getModeGroup() {
        return modeGroup;
    }

    public void setModeGroup(String modeGroup) {
        this.modeGroup = modeGroup;
    }
}
