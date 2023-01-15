package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String INBOX = "content://sms/inbox";
    private static final String TAG = "MainActivity";
    TextView textView;
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textVie);
        textView2 = findViewById(R.id.textVie2);
        textView3 = findViewById(R.id.textVie3);
        Log.i(TAG,"OnCreate");
        printSms();
        printContacts();
        
        contentFromUserDictionary();
        

    }

    private void contentFromUserDictionary() {
        String[] mProjection =
                {
                        UserDictionary.Words._ID,    // Contract class constant for the _ID column name
                        UserDictionary.Words.WORD,   // Contract class constant for the word column name
                        UserDictionary.Words.LOCALE  // Contract class constant for the locale column name
                };

        Cursor cursor = getContentResolver().query(UserDictionary.Words.CONTENT_URI,mProjection,null,null,null);
        StringBuilder stringBuilder = new StringBuilder();

        while (cursor.moveToNext()){
            for (int i =0; i<cursor.getCount();i++){
                stringBuilder.append("id "+cursor.getString(0));
                stringBuilder.append("word "+cursor.getString(1));
                stringBuilder.append("local "+cursor.getString(2)+"\n");
            }
        }

        textView3.setText(stringBuilder);
    }

    private void printContacts() {
        String projection[] ={ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,projection,null,null,null);

        if (cursor == null){

        }
        StringBuilder stringBuilder = new StringBuilder();


        while (cursor.moveToNext()){
                    stringBuilder.append("Contact name: ");
                    stringBuilder.append(cursor.getString(0));
                    stringBuilder.append(" tel: ");
                    stringBuilder.append(cursor.getInt(1));
                    stringBuilder.append("\n");
        }
        textView.setText(stringBuilder);

    }

    private void printSms() {

        Cursor cursor = getContentResolver().query(Uri.parse(INBOX),null,null,null,null);

        if (cursor == null){
            Log.i(TAG,"Unable to read sms");
        }

            if (cursor.moveToFirst()){
              do {
                  StringBuilder stringBuilder = new StringBuilder();
                  for (int i=0;i<cursor.getColumnCount();i++ ){
                        stringBuilder.append("SMS: ");
                        stringBuilder.append("Colum name: ");
                        stringBuilder.append(cursor.getColumnName(i));
                        stringBuilder.append("\nInside This colum: ");
                        stringBuilder.append(cursor.getString(i));
                        stringBuilder.append("\n");
}
                  stringBuilder.append("\n");

                  Log.i(TAG,"read sms: "+stringBuilder);
                  textView2.setText(stringBuilder);
              }while (cursor.moveToNext());
            }
        cursor.close();

    }
}
