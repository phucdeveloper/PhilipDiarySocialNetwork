package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.GroupActivity;
import com.example.phucnguyen.chichisocialnetwork.adapter.GroupAdapter;
import com.example.phucnguyen.chichisocialnetwork.model.Group;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.callback.OnClickListener;

import java.util.ArrayList;

public class FragmentGroup extends Fragment {

    RecyclerView recyclerViewListGroup;
    Button btnCreateGroup, btnYourGroup, btnDiscover;

    User user;

  /*  public FragmentGroup(User user){
        this.user = user;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        btnCreateGroup = view.findViewById(R.id.button_create_group);
        btnYourGroup = view.findViewById(R.id.button_your_group);
        btnDiscover = view.findViewById(R.id.button_discover);
        recyclerViewListGroup = view.findViewById(R.id.recyclerview_list_group);
        recyclerViewListGroup.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewListGroup.setLayoutManager(layoutManager);

        ArrayList<Group> arrayList = new ArrayList<>();

        GroupAdapter adapter = new GroupAdapter(arrayList, getContext());
        recyclerViewListGroup.setAdapter(adapter);

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickListener onClickListener = (OnClickListener) getContext();
                if(onClickListener != null)
                    onClickListener.onClick(user, 456);
            }});

        btnYourGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GroupActivity)getActivity()).setPostionViewPager(2);
            }
        });

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GroupActivity)getActivity()).setPostionViewPager(1);
            }
        });

        return view;
    }
}
