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

public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.ViewHolder> {

    ArrayList<String> array_background;
    Context context;
    OnBackgroundListener onBackgroundListener;

    public BackgroundAdapter(ArrayList<String> array_background, Context context){
        this.array_background = array_background;
        this.context = context;
    }

    public void setOnBackgroundListener(OnBackgroundListener onBackgroundListener) {
        this.onBackgroundListener = onBackgroundListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_background, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(array_background.get(position)).into(holder.imgBackground);
        holder.imgBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onBackgroundListener != null){
                    onBackgroundListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array_background.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgBackground;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBackground = itemView.findViewById(R.id.item_background);

        }
    }

    public interface OnBackgroundListener{
        void onItemClick(int position);
    }
}
