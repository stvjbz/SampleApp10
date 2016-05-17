package com.stvjbz.sampleapp10;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;


/**
 * Created by stvjbz on 16/05/18.
 */
public class JogRecordContentProvider extends ContentProvider {
    private DatabaseHelper mDbHelper;

    private static final int SAMPLEAPP10 = 10;
    private static final int SAMPLEAPP10_ID = 20;
    private static final String AUTHORITY = "com.stvjbz.sampleapp10.JogRecordContentProvider";

    private static final String BASE_PATH = "sampleapp10";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final UriMatcher uriMathcer = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMathcer.addURI(AUTHORITY, BASE_PATH, SAMPLEAPP10);
        uriMathcer.addURI(AUTHORITY, BASE_PATH + "/#", SAMPLEAPP10_ID);
    }
    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return false;
    }
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(DatabaseHelper.TABLE_SAMPLEAPP10);

        int uriType = uriMathcer.match(uri);
        switch (uriType) {
            case SAMPLEAPP10:
                break;
            case SAMPLEAPP10_ID:
                queryBuilder.appendWhere(DatabaseHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);

        return cursor;
    }
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = uriMathcer.match(uri);
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case SAMPLEAPP10:
                id = sqlDB.insert(DatabaseHelper.TABLE_SAMPLEAPP10, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Nullable
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    };
}
