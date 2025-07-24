package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Linhkien;

import java.util.List;

/**
 * DAO cho bảng Linhkien, định nghĩa các thao tác với database.
 */
@Dao
public interface LinhkienDAO {
    // Thêm mới một linh kiện
    @Insert
    void insert(Linhkien linhkien);

    // Cập nhật thông tin linh kiện
    @Update
    void update(Linhkien linhkien);

    // Xóa một linh kiện
    @Delete
    void delete(Linhkien linhkien);

    // Lấy toàn bộ danh sách linh kiện
    @Query("SELECT * FROM " + Linhkien.TABLE_NAME)
    List<Linhkien> getAll();
} 