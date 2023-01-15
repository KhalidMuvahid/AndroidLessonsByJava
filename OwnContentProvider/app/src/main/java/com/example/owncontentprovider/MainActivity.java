package com.example.owncontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.owncontentprovider.data.BookTable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  button = findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = getContentResolver().query(BookContentProvider.CONTENT_URI,null,null,null,null);

                if (cursor != null){
                    int count = cursor.getCount();
                    ContentValues values= new ContentValues();
                    values.put(BookTable.COLUMN_NAME,"ColumName: "+count);
                    values.put(BookTable.COLUMN_ISBN,"COLUMN_ISBN: "+count);
                    values.put(BookTable.COLUMN_DESCRIPTION,"COLUMN_DESCRIPTION: "+count);
                    getContentResolver().insert(BookContentProvider.CONTENT_URI,values);

                    cursor.close();
                }


            }
        });
    }
}