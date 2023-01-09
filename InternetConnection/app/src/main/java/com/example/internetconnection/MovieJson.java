package com.example.internetconnection;

import com.google.gson.annotations.SerializedName;

public class MovieJson {

    public int id;
    public String title;
    @SerializedName("img")
    public String parseUrl;

}
