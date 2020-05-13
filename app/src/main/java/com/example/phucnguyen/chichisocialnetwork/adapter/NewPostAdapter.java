package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.activity.CreateNewPostActivity;
import com.example.phucnguyen.chichisocialnetwork.model.NewPost;
import com.example.phucnguyen.chichisocialnetwork.R;

import java.util.ArrayList;

public class NewPostAdapter extends RecyclerView.Adapter<NewPostAdapter.ViewHolder> {

    ArrayList<NewPost> arrayList;
    Context context;

    public NewPostAdapter(ArrayList<NewPost> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.txtName.setText("Thêm vào tin");
            Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgImageBackground);
            holder.imgImageAvatar.setImageResource(R.drawable.icon_add_new_post);
        } else {
            holder.txtName.setText(arrayList.get(position).getName());
            Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgImageBackground);
            Glide.with(context).load(arrayList.get(position).getAvatar()).into(holder.imgImageAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView imgImageAvatar, imgImageBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textview_text_nickname);
            imgImageAvatar = itemView.findViewById(R.id.imageview_avatar);
            imgImageBackground = itemView.findViewById(R.id.imageview_background_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position == 0) {
                        Intent intent = new Intent(context, CreateNewPostActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Cam on ban da xem tin cua toi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
