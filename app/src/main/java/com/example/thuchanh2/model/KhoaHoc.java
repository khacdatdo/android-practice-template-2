package com.example.thuchanh2.model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {
    private int ma;
    private ChuyenNganh chuyenNganh;
    private String ten, ngayBatDau, hocPhi;
    private int kichHoat;

    public KhoaHoc(
            int ma,
            String ten,
            ChuyenNganh chuyenNganh,
            String ngayBatDau,
            String hocPhi,
            int kichHoat
    ) {
        this.ma = ma;
        this.chuyenNganh = chuyenNganh;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.hocPhi = hocPhi;
        this.kichHoat = kichHoat;
    }

    public KhoaHoc(
            String ten,
            ChuyenNganh chuyenNganh,
            String ngayBatDau,
            String hocPhi,
            int kichHoat
    ) {
        this.chuyenNganh = chuyenNganh;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.hocPhi = hocPhi;
        this.kichHoat = kichHoat;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public ChuyenNganh getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(ChuyenNganh chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(String hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(int kichHoat) {
        this.kichHoat = kichHoat;
    }
}
