package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.SignUpActivity;
import com.example.phucnguyen.chichisocialnetwork.callback.OnSendDataClickListener;

public class FragmentEmailPassword extends Fragment {

    Button btnContinue;
    EditText edtEmailPhoneNumber, edtPassword, edtConfirmPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_password, container, false);
        edtEmailPhoneNumber = view.findViewById(R.id.edittext_email);
        edtPassword = view.findViewById(R.id.edittext_password);
        edtConfirmPassword = view.findViewById(R.id.edittext_confirm_password);

        btnContinue = view.findViewById(R.id.button_continue);

        final OnSendDataClickListener onSendDataClickListener = (OnSendDataClickListener)getContext();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmailPhoneNumber.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(getContext(), "Mời bạn nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(!confirmPassword.equals(password)){
                    Toast.makeText(getContext(), "Confirm Password không trùng khớp", Toast.LENGTH_SHORT).show();
                }
                else{
                    ((SignUpActivity)getActivity()).setPositionViewPager(1);
                    if(onSendDataClickListener != null){
                        onSendDataClickListener.onSendDataEmail(email, password);
                    }
                }
            }
        });
        return view;
    }
}
