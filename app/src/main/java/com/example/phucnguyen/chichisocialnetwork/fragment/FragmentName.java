package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.SignUpActivity;
import com.example.phucnguyen.chichisocialnetwork.callback.OnSendDataClickListener;

public class FragmentName extends Fragment {
    EditText edtFirstName, edtLastName;
    Button btnContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        edtFirstName = view.findViewById(R.id.edittext_first_name);
        edtLastName = view.findViewById(R.id.edittext_last_name);
        btnContinue = view.findViewById(R.id.button_continue);

        final OnSendDataClickListener onSendDataClickListener = (OnSendDataClickListener)getContext();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = edtFirstName.getText().toString();
                String lastname = edtLastName.getText().toString();
                String name = firstname + " " + lastname;
                ((SignUpActivity)getActivity()).setPositionViewPager(2);
                if(onSendDataClickListener != null){
                    onSendDataClickListener.onSendDataName(name);
                }

            }
        });
        return view;
    }
}
