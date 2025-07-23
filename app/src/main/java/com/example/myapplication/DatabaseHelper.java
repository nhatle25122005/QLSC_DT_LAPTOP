package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ThongKeDB.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "phieusuachua";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TENSP = "tensp";
    public static final String COLUMN_GIA = "gia";
    public static final String COLUMN_TINHTRANG = "tinhtrang";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TENSP + " TEXT, " +
                COLUMN_GIA + " INTEGER, " +
                COLUMN_TINHTRANG + " TEXT, " +
                "ten_khach_hang TEXT, " +
                "ten_nhan_vien TEXT)";
        db.execSQL(createTable);
    }


    // Xử lý khi nâng cấp database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
