package com.example.owncontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.owncontentprovider.data.BookDatabaseHelper;
import com.example.owncontentprovider.data.BookTable;

import java.util.Arrays;
import java.util.HashSet;

public class BookContentProvider extends ContentProvider {

    private BookDatabaseHelper database;
    private static final String AUTHORITY = "com.example.owncontentprovider";
    private static final String BASE_PATH = "books";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int BOOKS = 1;
    private static final int BOOKS_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, BASE_PATH, BOOKS);
        sUriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", BOOKS_ID);
    }


    @Override
    public boolean onCreate() {
        database = new BookDatabaseHelper(getContext());
        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        checkColumns(projection);

        queryBuilder.setTables(BookTable.TABLE_BOOK);

        int utiType = sUriMatcher.match(uri);
        switch (utiType) {
            case BOOKS: {
                break;
            }
            case BOOKS_ID: {
                queryBuilder.appendWhere(BookTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown URI: " + uri);
            }
        }
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case BOOKS:
                id = sqlDB.insert(BookTable.TABLE_BOOK, null, values);
                break;
            default:
                throw new IllegalArgumentException("unknown Uri" + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase db = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case BOOKS: {
                rowsDeleted = db.delete(BookTable.TABLE_BOOK, selection, selectionArgs);
                break;
            }
            case BOOKS_ID: {
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(BookTable.TABLE_BOOK, BookTable.COLUMN_ID + "=" + id, null);
                } else {
                    rowsDeleted = db.delete(BookTable.TABLE_BOOK, BookTable.COLUMN_ID + "=" + id + "and" + selection, selectionArgs);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown Uri" + uri);
            }
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase db = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case BOOKS: {
                rowsUpdated = db.update(BookTable.TABLE_BOOK,
                        values,
                        selection,
                        selectionArgs);
                break;
            }
            case BOOKS_ID: {
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(BookTable.TABLE_BOOK,
                            values,
                            BookTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = db.update(BookTable.TABLE_BOOK,
                            values,
                            BookTable.COLUMN_ID + "=" + id + "and" + selection,
                            selectionArgs);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown Uri" + uri);
            }
        }
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = {BookTable.COLUMN_NAME, BookTable.COLUMN_ISBN, BookTable.COLUMN_DESCRIPTION, BookTable.COLUMN_ID};

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<>(Arrays.asList(available));
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("unknown columns in projection");
            }
        }
    }
}
