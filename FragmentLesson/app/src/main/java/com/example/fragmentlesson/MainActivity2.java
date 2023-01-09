package com.example.fragmentlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new SecondFragment())
                .addToBackStack(null)
                .commit();

    }
}