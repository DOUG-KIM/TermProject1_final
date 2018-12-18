package com.example.deokwook.termproject1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "request.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table request(" +
                "r_number integer primary key autoincrement," +
                "r_name text," +
                "money text," +
                "r_text text," +
                "phone text," +
                "latitude text," +
                "longitude text" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists request");
        onCreate(db);
    }

    public void insertDB(String latitude, String longitude, String r_name, String money, String r_text, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        contentValues.put("r_name", r_name);
        contentValues.put("money", money);
        contentValues.put("r_text", r_text);
        contentValues.put("phone", phone);

        db.insert("request", null, contentValues);
    }

    public Cursor searchDB() {

        Cursor cursor; // 레코드의 포인터
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("select r_name, money, r_text, phone, latitude, longitude from request;", null); //질의문
        return cursor;
    }

    public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("request",null,null);
    }

}
