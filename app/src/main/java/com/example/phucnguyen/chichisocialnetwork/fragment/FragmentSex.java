package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.SignUpActivity;
import com.example.phucnguyen.chichisocialnetwork.callback.OnSendDataClickListener;

public class FragmentSex extends Fragment {

    Button btnContinue;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonCustom;

    String sex;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sex, container, false);
        btnContinue = view.findViewById(R.id.button_continue);
        radioButtonMale = view.findViewById(R.id.radiobutton_male);
        radioButtonFemale = view.findViewById(R.id.radiobutton_female);
        radioButtonCustom = view.findViewById(R.id.radiobutton_custom);

        radioButtonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = radioButtonMale.getText().toString();
            }
        });

        radioButtonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = radioButtonFemale.getText().toString();
            }
        });

        radioButtonCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = radioButtonCustom.getText().toString();
            }
        });

        final OnSendDataClickListener onSendDataClickListener = (OnSendDataClickListener)getContext();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(getContext(), "Sex: " + sex, Toast.LENGTH_SHORT).show();
                ((SignUpActivity)getActivity()).setPositionViewPager(3);
                if(onSendDataClickListener != null){
                    onSendDataClickListener.onSendDataSex(sex);
                }
            }
        });

        return view;
    }
}
