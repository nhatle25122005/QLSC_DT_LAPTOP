package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.HoaDon;

@Database(entities = {HoaDon.class}, version = 1)
public abstract class HoaDonDatabase extends RoomDatabase {

    private static HoaDonDatabase instance;

    public abstract HoaDonDao hoaDonDAO();

    public static synchronized HoaDonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HoaDonDatabase.class, "gym_database")
                    .allowMainThreadQueries() // dùng cho demo
                    .build();
        }
        return instance;
    }
}
