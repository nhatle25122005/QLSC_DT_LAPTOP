package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Dichvu;

import java.util.List;

@Dao

public interface DichvuDAO {
    @Insert
    void insertDichvu(Dichvu dichvu);

    @Query("SELECT * FROM dichvu")
    List<Dichvu> getListDichvu();

}
