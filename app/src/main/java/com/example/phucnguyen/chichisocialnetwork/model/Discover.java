package com.example.phucnguyen.chichisocialnetwork.model;

import java.util.ArrayList;

public class Discover {
    private String typeGroup;
    private String description;
    private ArrayList<TypeGroup> arrayList;


    public Discover(String typeGroup, String description, ArrayList<TypeGroup> arrayList) {
        this.typeGroup = typeGroup;
        this.description = description;
        this.arrayList = arrayList;
    }

    public String getTypeGroup() {
        return typeGroup;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<TypeGroup> getArrayList() {
        return arrayList;
    }
}
