package com.example.phucnguyen.chichisocialnetwork.model;

public class TypeGroup {
    private String typeGroup;
 //   private String image;
    private int image;

    public TypeGroup(String typeGroup, int image) {
        this.typeGroup = typeGroup;
        this.image = image;
    }

    public String getTypeGroup() {
        return typeGroup;
    }

    public int getImage() {
        return image;
    }
}
