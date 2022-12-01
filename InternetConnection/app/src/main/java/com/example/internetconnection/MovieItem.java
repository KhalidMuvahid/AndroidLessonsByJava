package com.example.internetconnection;

import com.google.gson.annotations.SerializedName;

public class MovieItem {

    int id;
    @SerializedName("title")
    String name;
    @SerializedName("img")
    String image;


    public MovieItem(int id,String name,String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
