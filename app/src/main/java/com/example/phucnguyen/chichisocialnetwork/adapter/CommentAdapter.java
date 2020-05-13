package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.model.Comment;
import com.example.phucnguyen.chichisocialnetwork.utils.Constant;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<Comment> arrayList;
    Context context;
    OnReplyClickListener onReplyClickListener;

    public CommentAdapter(ArrayList<Comment> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setOnReplyClickListener(OnReplyClickListener onReplyClickListener) {
        this.onReplyClickListener = onReplyClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == Constant.ITEM_COMMENT_TEXT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_text, parent, false);
            return new ViewHolder(view, viewType);
        }
        else if(viewType == Constant.ITEM_COMMENT_IMAGE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_image, parent, false);
            return new ViewHolder(view, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(arrayList.get(position).getViewType() == Constant.ITEM_COMMENT_TEXT){
            Glide.with(context).load(arrayList.get(position).getAvatarAccount()).into(holder.imgAvatarComment);
            holder.txtContentComment.setText(arrayList.get(position).getContentComment());
            holder.txtNameAccount.setText(arrayList.get(position).getNameAccount());

            holder.txtReplyComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onReplyClickListener != null){
                        onReplyClickListener.OnReplyClick(position);
                    }
                }
            });
        }
        else{
            Glide.with(context).load(arrayList.get(position).getAvatarAccount()).into(holder.imgAvatarComment);
            holder.txtNameAccount.setText(arrayList.get(position).getNameAccount());
            Glide.with(context).load(arrayList.get(position).getContentComment()).into(holder.imgImageComment);

            holder.txtReplyComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onReplyClickListener != null){
                        onReplyClickListener.OnReplyClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return arrayList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAvatarComment, imgImageComment;
        TextView txtNameAccount, txtContentComment, txtReplyComment, txtLikeComment;
        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            txtReplyComment = itemView.findViewById(R.id.item_reply_comment);
            txtLikeComment = itemView.findViewById(R.id.item_like_comment);

            if(viewType == Constant.ITEM_COMMENT_TEXT){
                imgAvatarComment = itemView.findViewById(R.id.item_avatar_comment);
                txtNameAccount = itemView.findViewById(R.id.item_textview_name_account_comment);
                txtContentComment = itemView.findViewById(R.id.item_textview_comment);
            }
            else if(viewType == Constant.ITEM_COMMENT_IMAGE){
                imgAvatarComment = itemView.findViewById(R.id.item_avatar_comment);
                txtNameAccount = itemView.findViewById(R.id.item_textview_name_account_comment);
                imgImageComment = itemView.findViewById(R.id.item_imageview_image_comment);
            }
        }
    }

    public interface OnReplyClickListener{
        void OnReplyClick(int position);
    }
}
