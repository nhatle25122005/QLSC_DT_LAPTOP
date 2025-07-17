package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dichvu")
public class Dichvu {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String maPhieu;

    private String tenSanPham;
    private String yeuCau;
    private int thanhTien;
    private String ngayTao;
    private String tinhTrang;
    private String ghiChu;

    //Thêm constructor rỗng để Room có thể tạo object
    public Dichvu() {
    }

    public Dichvu(String maPhieu, String tenSanPham, String yeuCau, int thanhTien, String ngayTao, String tinhTrang, String ghiChu) {
        this.maPhieu = maPhieu;
        this.tenSanPham = tenSanPham;
        this.yeuCau = yeuCau;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.tinhTrang = tinhTrang;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }
}
