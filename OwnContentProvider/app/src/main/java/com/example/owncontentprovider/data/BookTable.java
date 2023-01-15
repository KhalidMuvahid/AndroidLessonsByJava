package com.example.owncontentprovider.data;

import android.database.sqlite.SQLiteDatabase;

public class BookTable {

    public static final String TABLE_BOOK = "books";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ISBN = "isbn";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String DATABASE_CREATE = "CREATE TABLE "
            +TABLE_BOOK+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_NAME+" TEXT NOT NULL,"
            +COLUMN_ISBN+" TEXT NOT NULL,"
            +COLUMN_DESCRIPTION+" TEXT NOT NULL"+");";


    public static void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " +TABLE_BOOK);
        onCreate(database);
    }

}
