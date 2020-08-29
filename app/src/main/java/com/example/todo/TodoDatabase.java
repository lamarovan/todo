package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public long addNote(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TASK,todo.getTask());
        c.put(KEY_DATE,todo.getDate());
        c.put(KEY_TIME,todo.getTime());

        long ID = db.insert(DATABASE_TABLE,null,c);
        Log.d("inserted,", "ID -> "+ID);
        return ID;
    }

//    public List<Todo> getTask() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<Todo> allTask = new ArrayList<>();
//        String query = "SELECT * FROM "+DATABASE_TABLE;
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor != null){
//            do {
//                Todo todo = new Todo();
//                todo.setID(cursor.getLong(0));
//                todo.setTask(cursor.getString(1));
//                todo.setDate(cursor.getString(2));
//                todo.setTime(cursor.getString(3));
//
//                allTask.add(todo);
//            } while (cursor.moveToNext());
//            return allTask;
//        }
//    }

}