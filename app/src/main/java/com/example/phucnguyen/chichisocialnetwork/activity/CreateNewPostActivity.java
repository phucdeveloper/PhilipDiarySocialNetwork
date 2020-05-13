package com.example.phucnguyen.chichisocialnetwork.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.adapter.ImageAdapter;

import java.util.ArrayList;

public class CreateNewPostActivity extends AppCompatActivity {

    RecyclerView recyclerViewListImage, recyclerViewListTypeNewPost;
    ArrayList<String> arrayList = new ArrayList<>();

    static int REQUESTCODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_post);

        initView();

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Get All Images"), REQUESTCODE);

        setUpRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){
            Uri uri = data.getData();
            String imagePath = getPath(uri);
            arrayList.add(imagePath);
        }
    }

    public String getPath(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void setUpRecyclerView(){
        recyclerViewListImage.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerViewListImage.setLayoutManager(layoutManager);

        ImageAdapter adapter = new ImageAdapter(arrayList);
        recyclerViewListImage.setAdapter(adapter);
    }

    public void onBackClick(View view){
        finish();
    }

    private void initView() {
        recyclerViewListImage = findViewById(R.id.recyclerview_list_image);
        recyclerViewListTypeNewPost = findViewById(R.id.recyclerview_list_type_new_post);
    }
}
