package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

/**
 * Model đại diện cho một Linh kiện.
 * Sử dụng annotation Room để ánh xạ với database.
 */
@Entity(tableName = Linhkien.TABLE_NAME)
public class Linhkien {
    // Định nghĩa tên bảng và các cột dưới dạng constant để dùng chung
    public static final String TABLE_NAME = "tb_linhkien";
    public static final String COL_ID = "id";
    public static final String COL_TEN = "ten";
    public static final String COL_GIA = "gia";
    public static final String COL_SOLUONG = "soluong";
    public static final String COL_MOTA = "mota";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)
    private int id;

    @ColumnInfo(name = COL_TEN)
    private String ten;

    @ColumnInfo(name = COL_GIA)
    private double gia;

    @ColumnInfo(name = COL_SOLUONG)
    private int soluong;

    @ColumnInfo(name = COL_MOTA)
    private String mota;

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }
    public int getSoluong() { return soluong; }
    public void setSoluong(int soluong) { this.soluong = soluong; }
    public String getMota() { return mota; }
    public void setMota(String mota) { this.mota = mota; }
} 