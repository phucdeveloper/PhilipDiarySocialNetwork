package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phucnguyen.chichisocialnetwork.R;


public class FragmentAddMember extends Fragment {

    View view;
    public FragmentAddMember() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_member, container, false);
        return view;
    }
}
