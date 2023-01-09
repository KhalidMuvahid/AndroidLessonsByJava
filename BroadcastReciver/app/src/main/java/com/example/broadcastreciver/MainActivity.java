package com.example.broadcastreciver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAct= findViewById(R.id.btAct);

        btAct.setOnClickListener(v->{
            Intent intent =new Intent(this,BatteryActivity.class);
            startActivity(intent);
        });
    }
}