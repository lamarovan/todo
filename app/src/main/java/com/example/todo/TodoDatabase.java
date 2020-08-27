package com.example.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "tododb";
    private static final String DATABASE_TABLE = "todotable";

    //Column name for the table
    private static final String KEY_ID = "id";
    private static final String KEY_TASK = "task";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    TodoDatabase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DATABASE_NAME + " (" + KEY_ID + " INT PRIMARY KEY, " +
                KEY_TASK + " TEXT," +
                KEY_DATE + " TEXT,"+
                KEY_TIME + " TEXT" + ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i >= i1)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS todotable");
        onCreate(sqLiteDatabase);
    }
}