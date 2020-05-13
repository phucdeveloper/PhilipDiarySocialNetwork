package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.GroupDetailActivity;
import com.example.phucnguyen.chichisocialnetwork.model.Group;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    ArrayList<Group> arrayList;
    Context context;

    public GroupAdapter(ArrayList<Group> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNameGroup.setText(arrayList.get(position).getNameGroup());
        holder.imgAvatarGroup.setImageResource(arrayList.get(position).getAvatarGroup());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAvatarGroup;
        TextView txtNameGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatarGroup = itemView.findViewById(R.id.item_imageview_avatar_group);
            txtNameGroup = itemView.findViewById(R.id.item_textview_name_group);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, GroupDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("group", arrayList.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }
}
