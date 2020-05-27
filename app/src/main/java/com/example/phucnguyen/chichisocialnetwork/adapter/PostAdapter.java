package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.PostWithVideo;
import com.example.phucnguyen.chichisocialnetwork.model.Timeline;
import com.example.phucnguyen.chichisocialnetwork.model.User;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    ArrayList<Timeline> arrayList;
    Context context;
    User user;
    int numberImage, i;

    public PostAdapter(ArrayList<Timeline> arrayList, Context context, User user) {
        this.arrayList = arrayList;
        this.context = context;
        this.user = user;
    }

    @Override
    public int getItemViewType(int position) {
        i=position;
        return arrayList.get(position).getViewType();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){

            case Constant.ITEM_POST_WITH_IMAGE:
                if(arrayList.get(i).getPostImage().getArrayList().size() == 1){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_with_one_image, parent, false);
                    return new PostWithOneImageViewHolder(view);
                }

                if(arrayList.get(i).getPostImage().getArrayList().size() == 2){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_with_two_image, parent, false);
                    return new PostWithTwoImageViewHolder(view);
                }

                if(arrayList.get(i).getPostImage().getArrayList().size() == 3){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_with_three_image, parent, false);
                    return new PostWithThreeImageViewHolder(view);
                }

                if(arrayList.get(i).getPostImage().getArrayList().size() > 3){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_with_image, parent, false);
                    return new PostWithImageViewHolder(view);
                }


            case Constant.ITEM_POST_WITH_TEXT:
                if(arrayList.get(i).getPostText().getBackground() != null){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text_with_background, parent, false);
                    return new PostTextWithBackgroundViewHolder(view);
                }
                else{
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_with_text, parent, false);
                    return new PostWithTextViewHolder(view);
                }

             case Constant.ITEM_POST_WITH_VIDEO:
                  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_with_video, parent, false);
                  return new PostWithVideoViewHolder(view);


            case Constant.ITEM_POST_TEXT_SHARED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_share_post_text, parent, false);
                return new PostTextShareViewHolder(view);
            case Constant.ITEM_POST_IMAGE_SHARED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_share_post_image, parent, false);
                return new PostImageShareViewHolder(view);


            case Constant.ITEM_POST_UPDATE_AVATAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_update_avatar, parent, false);
                return new PostUpdateAvatarViewHolder(view);

                
            case Constant.ITEM_POST_UPDATE_BACKGROUND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_update_background, parent, false);
                return new PostUpdateBackgroundViewHolder(view);

            default: throw new IllegalArgumentException();
        }


    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.sendData(arrayList.get(position), position, context, user);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
