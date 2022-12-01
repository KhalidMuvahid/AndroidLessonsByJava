package com.example.internetconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieItem> movieItems = new ArrayList<>();

    RecyclerView rc;
    RcAdapter rcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().movieServices.getMovies().enqueue(new Callback<List<MovieItem>>() {
            @Override
            public void onResponse(Call<List<MovieItem>> call, Response<List<MovieItem>> response) {
                if (response.isSuccessful()){
                    List<MovieItem> movieList = response.body();
                    movieItems.clear();
                    for (MovieItem movie:movieList){
                        movieItems.add(movie);
                    }

                    rc.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MovieItem>> call, Throwable t) {

            }
        });




        rc = findViewById(R.id.recyclerView);
        rcAdapter = new RcAdapter(movieItems);

        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(rcAdapter);

    }
}