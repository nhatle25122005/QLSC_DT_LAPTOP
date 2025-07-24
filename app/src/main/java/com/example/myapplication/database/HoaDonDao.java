package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.HoaDon;
import java.util.List;

@Dao
public interface HoaDonDao {
    @Insert
    void insert(HoaDon hoaDon);

    @Query("SELECT * FROM hoadon")
    List<HoaDon> getAll();
}
