package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.callback.OnClickListener;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentDiscover;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentGroup;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentSettingGroup;
import com.example.phucnguyen.chichisocialnetwork.fragment.FragmentYourGroup;
import com.example.phucnguyen.chichisocialnetwork.model.User;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity implements OnClickListener {

    ViewPager viewPager;

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        viewPager = findViewById(R.id.container);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
    }

    public void setPostionViewPager(int postion){
        viewPager.setCurrentItem(postion);
    }

    @Override
    public void onClick(User user, int code) {
        //Nhan interface tu fragment group va di chuyen den man hinh tao group
        if(code == 456){
            Intent intent = new Intent(GroupActivity.this, CreateGroupActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(String idUser) {

    }

    class PagerAdapter extends FragmentPagerAdapter{
        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                  //  return new FragmentGroup(user);
                return new FragmentGroup();
                case 1:
                    return new FragmentDiscover();
                case 2:
                    return new FragmentYourGroup();
                case 3:
                    return new FragmentSettingGroup();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
