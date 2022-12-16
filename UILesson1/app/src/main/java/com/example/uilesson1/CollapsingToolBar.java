package com.example.uilesson1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class CollapsingToolBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colapsing_tool_bar);
        Toolbar toolbar = findViewById(R.id.collapse_toolbar);
        setSupportActionBar(toolbar);
    }
}