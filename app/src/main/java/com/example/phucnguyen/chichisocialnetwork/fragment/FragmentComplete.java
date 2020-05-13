package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class FragmentComplete extends Fragment {

    private Button btnComplete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete, container, false);

        btnComplete = view.findViewById(R.id.button_complete);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSignUpClickListener onSignUpClickListener = (OnSignUpClickListener) getContext();
                if (onSignUpClickListener != null) {
                    onSignUpClickListener.onSuccess(true);
                }
            }
        });
        return view;
    }

    public interface OnSignUpClickListener {
        void onSuccess(boolean isClick);
    }
}
