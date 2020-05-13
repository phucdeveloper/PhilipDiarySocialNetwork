package com.example.phucnguyen.chichisocialnetwork.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class User implements Serializable {
    private String uid;
    private String phonenumberAndemail;
    private String password;
    private String nickname;
    private String avatar;
    private String background;
    private String sex;
    private String birthday;
    private String address;
    private String country;
    private String work;
    private String maritalStatus;
    private String literacy;
    private String slogan;

    public User(){}

    //Ham khoi tao nay duoc dung khi de cho nguoi dung dang ky
    public User(String uid, String phonenumberAndemail, String password, String nickname, String sex, String birthday) {
        this.uid = uid;
        this.phonenumberAndemail = phonenumberAndemail;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
    }

    //Ham khoi tao nay duoc dung khi nguoi dung cap nhat thong tin cua minh
    public User(String uid, String phonenumberAndemail, String password, String nickname, String avatar, String background, String sex, String birthday, String address, String country, String work, String maritalStatus, String literacy) {
        this.uid = uid;
        this.phonenumberAndemail = phonenumberAndemail;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.background = background;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.country = country;
        this.work = work;
        this.maritalStatus = maritalStatus;
        this.literacy = literacy;
    }

    public User(String uid, String nickname, String avatar) {
        this.uid = uid;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhonenumberAndemail() {
        return phonenumberAndemail;
    }

    public void setPhonenumberAndemail(String phonenumberAndemail) {
        this.phonenumberAndemail = phonenumberAndemail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getLiteracy() {
        return literacy;
    }

    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
