package com.example.owncontentreciver;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String AUTHORITY = "com.example.owncontentprovider";
    private static final String BASE_PATH = "books";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor  = getContentResolver().query(CONTENT_URI,null,null,null,null);

        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                Log.d(TAG,cursor.getString(1));
                Log.d(TAG,cursor.getString(2));
                Log.d(TAG,cursor.getString(3));
            }
        }


    }
}