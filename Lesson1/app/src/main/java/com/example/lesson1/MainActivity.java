package com.example.lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity{
    private static final String MY_CONS = "myCont";
    final static String TAG = MainActivity.class.getSimpleName();

    EditText editText;
    Button shareBt;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        shareBt = findViewById(R.id.shareBt);


        if (savedInstanceState != null){
            String oldMessage = savedInstanceState.getString(MY_CONS);

            if (!TextUtils.isEmpty(oldMessage)){
                editText.setText(savedInstanceState.getString(MY_CONS));
            }
            Log.d(TAG,"onSaveInstanceSate:["+oldMessage+"]");
        }




    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String saveMessage = editText.getText().toString();
        outState.putString(MY_CONS,saveMessage);

        Log.d(TAG,"onSaveInstanceSate:["+saveMessage+"]");
    }


    public void startIntent(View view) {
        String textMessage = "Our text";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,textMessage);
        intent.setType("text/plain");

        String title = "Share";
        Intent chooser = Intent.createChooser(intent,title);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(chooser);
        }

    }

    final static  int OUR_REQUIRE_CODE = 42;
    public void secondActListener(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        startActivityForResult(intent,OUR_REQUIRE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OUR_REQUIRE_CODE){
            String answer = null;

            if (resultCode == RESULT_OK && data != null){
                answer = data.getStringExtra("answer");
                editText.setText(answer);
            }
            Log.d(TAG,answer);
        }
    }
}