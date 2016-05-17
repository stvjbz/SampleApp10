package com.stvjbz.sampleapp10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stvjbz on 16/05/15.
 */
public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String DBNAME = "sampleapp10.db";
    private static final int DBVERSION = 1;
    public static final String TABLE_SAMPLEAPP10= "sampleapp10";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ELAPSEDTIME = "eltime";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_ADDRESS = "address";
    public static final String CREATE_TABLE_SQL =
            "create table "  + TABLE_SAMPLEAPP10 + " "
                + "(" + COLUMN_ID + " integer primary key autoincrement,"
                + COLUMN_DATE + " text not null,"
                + COLUMN_ELAPSEDTIME + " text not null,"
                + COLUMN_DISTANCE + " real not null,"
                + COLUMN_ADDRESS + " text null)";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
