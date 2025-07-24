package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Linhkien;

/**
 * Database cho Linhkien, sử dụng Room.
 */
@Database(entities = {Linhkien.class}, version = 1, exportSchema = false)
public abstract class LinhkienDatabase extends RoomDatabase {
    private static final String DB_NAME = "linhkien_db";
    private static LinhkienDatabase instance;

    public abstract LinhkienDAO linhkienDAO();

    // Singleton pattern để đảm bảo chỉ có một instance database
    public static synchronized LinhkienDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LinhkienDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
} 