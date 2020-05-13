package com.example.phucnguyen.chichisocialnetwork.callback;

public interface OnSendDataClickListener {
    void onSendDataName(String name);
    void onSendDataEmail(String email, String password);
    void onSendDataSex(String sex);
    void onSendDataBirthDay(String birthday);
}
