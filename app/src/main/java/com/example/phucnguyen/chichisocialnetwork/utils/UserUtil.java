package com.example.phucnguyen.chichisocialnetwork.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.gson.Gson;

public class UserUtil {
    private User user;

    public UserUtil(User user){
        this.user = user;
    }

  /*  public static void setUser(User user, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        editor.putString("user", jsonUser);
        editor.apply();
    }*/

    public static void setIdUser(String str, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("idUser", Context.MODE_PRIVATE).edit();
        editor.putString("id", str);
        editor.apply();
    }

    public static String getIdUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences("idUser", Context.MODE_PRIVATE);
        String id = preferences.getString("id", null);
        return id;
    }

 /*   public static User getUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String jUser = preferences.getString("user", null);

        if(jUser == null){
            return null;
        }
        return new Gson().fromJson(jUser, User.class);
    }*/
}
