package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hoadon")
public class HoaDon {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String ngayLap;
    public double tongTien;
    public int maKhach;
    public int maNhanVien;
    public String ghiChu;

    public HoaDon(String ngayLap, double tongTien, int maKhach, int maNhanVien, String ghiChu) {
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.maKhach = maKhach;
        this.maNhanVien = maNhanVien;
        this.ghiChu = ghiChu;
    }
}
