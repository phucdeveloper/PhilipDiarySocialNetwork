package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.GroupActivity;
import com.example.phucnguyen.chichisocialnetwork.activity.SignInActivity;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.UserUtil;
import com.example.phucnguyen.chichisocialnetwork.callback.OnClickListener;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentMenu extends Fragment {
    LinearLayout layoutMenu, layoutGroup;
    ImageView imgAvatar;
    TextView txtNickname, txtFriend;
    Button btnSignOut;

    User user;

    FirebaseAuth firebaseAuth;

    public FragmentMenu(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        layoutMenu = view.findViewById(R.id.linearlayout_avatar);
        layoutGroup = view.findViewById(R.id.layout_group);
        imgAvatar = view.findViewById(R.id.imageview_avatar);
        txtNickname = view.findViewById(R.id.textview_nickname);
        txtFriend = view.findViewById(R.id.textview_friend);
        btnSignOut = view.findViewById(R.id.button_sign_out);

        firebaseAuth = FirebaseAuth.getInstance();

        Glide.with(this).load(user.getAvatar()).into(imgAvatar);
        txtNickname.setText(user.getNickname());


        layoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickListener onClickListener = (OnClickListener) getContext();
                if (onClickListener != null)
                    onClickListener.onClick(user.getUid());
            }
        });

        layoutGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GroupActivity.class);
              //  intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getContext() != null){
                    if(UserUtil.getIdUser(getContext()) != null){
                        firebaseAuth.signOut();
                        UserUtil.setIdUser(null, getContext());
                        Intent intent = new Intent(getContext(), SignInActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });

        return view;
    }

}
