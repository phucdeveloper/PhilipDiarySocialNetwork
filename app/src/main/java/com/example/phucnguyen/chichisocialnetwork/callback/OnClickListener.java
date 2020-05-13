package com.example.phucnguyen.chichisocialnetwork.callback;

import com.example.phucnguyen.chichisocialnetwork.model.User;

public interface OnClickListener {
    void onClick(User user, int code);
    void onClick(String idUser);
}
