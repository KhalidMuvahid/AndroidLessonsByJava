package com.example.internetconnection;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static App instance;
    public MovieService movieServices;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initRetrofit();
    }

    public static App getInstance() {
        return instance;
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://my-json-server.typicode.com/denis-zhuravlev/json-placeholder/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieServices = retrofit.create(MovieService.class);
    }


}


