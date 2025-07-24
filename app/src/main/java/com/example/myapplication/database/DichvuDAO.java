package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Dichvu;

import java.util.List;

@Dao
public interface DichvuDAO {
    @Insert
    void insertDichvu(Dichvu dichvu);

    @Delete
    void deleteDichvu(Dichvu dichvu);

    @Update
    void updateDichvu(Dichvu dichvu);


    @Query("SELECT * FROM dichvu")
    List<Dichvu> getListDichvu();
}
