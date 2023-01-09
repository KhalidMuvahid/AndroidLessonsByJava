package com.example.internetconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieItem> movieItems = new ArrayList<>();

    RecyclerView rc;
    ProgressBar loader;
    RcAdapter rcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc = findViewById(R.id.recyclerView);
        rcAdapter = new RcAdapter(movieItems);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(rcAdapter);
        loader = findViewById(R.id.progress_circular);

        loader.setVisibility(View.VISIBLE);
        App.getInstance().movieService.getMovies().enqueue(new Callback<List<MovieJson>>() {
            @Override
            public void onResponse(Call<List<MovieJson>> call, Response<List<MovieJson>> response) {
                if (response.isSuccessful()){
                    List<MovieJson> movieJsonList = response.body();
                    movieItems.clear();
                    for (MovieJson movieJson : movieJsonList){
                        movieItems.add(new MovieItem(movieJson));
                    }

                    rc.getAdapter().notifyDataSetChanged();
                    loader.setVisibility(View.GONE);
                }else {
                    if (response.code() == 500){
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MovieJson>> call, Throwable t) {

            }
        });


    }
}