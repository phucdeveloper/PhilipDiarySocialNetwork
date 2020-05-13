package com.example.phucnguyen.chichisocialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phucnguyen.chichisocialnetwork.R;

import java.util.ArrayList;

public class ListImagePostAdapter extends RecyclerView.Adapter<ListImagePostAdapter.ViewHolder> {

    ArrayList<String> arrayList;
    Context context;

    public ListImagePostAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position)).into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        if (arrayList.size() > 1)
            return arrayList.size();
        else
            return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgImage = itemView.findViewById(R.id.imageview_image);
        }
    }
}
