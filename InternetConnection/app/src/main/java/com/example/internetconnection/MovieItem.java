package com.example.internetconnection;

import com.google.gson.annotations.SerializedName;

public class MovieItem {

    int id;
    String name;
    String image;


    public MovieItem(MovieJson movieJson){
        this.id = movieJson.id;
        this.name = movieJson.title;
        this.image = movieJson.parseUrl;
    }
}
