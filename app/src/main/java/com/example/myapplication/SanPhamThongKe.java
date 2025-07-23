package com.example.myapplication;

public class SanPhamThongKe {
    private String tenSP;
    private String tenKhachHang;
    private String tenNhanVien;
    private int giaTien;
    private String tinhTrang;



    public SanPhamThongKe(String tenSP, String tenKhachHang, String tenNhanVien, int giaTien, String tinhTrang) {
        this.tenSP = tenSP;
        this.tenKhachHang = tenKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.giaTien = giaTien;
        this.tinhTrang = tinhTrang;
    }

    public String getTenSP() {
        return tenSP;
    }
    public String getTenKhachHang() {
        return tenKhachHang;
    }


    public String getTenNhanVien() {
        return tenNhanVien;
    }


    public int getGiaTien() {
        return giaTien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public String getMaSP() {
        return tenSP.hashCode() + ""; // vẫn được, hoặc bạn có thể truyền từ cột `id`
    }
}
