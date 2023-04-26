package com.example.thuchanh2.model;

public enum ChuyenNganh {
    CNTT("Công nghệ thông tin"),
    KT("Kế toán"),
    TKDH("Thiết kế đồ hoạ"),
    QTKD("Quản trị kinh doanh");

    private String description;

    ChuyenNganh(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}