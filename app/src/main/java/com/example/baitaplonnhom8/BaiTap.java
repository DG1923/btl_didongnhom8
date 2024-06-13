package com.example.baitaplonnhom8;

public class BaiTap {
    private String tenBaiTap;
    private String huongDan;
    private int hinhAnh;
    private int thoiGianYeuCau;
    private String trangThai;

    public BaiTap(String tenBaiTap, String huongDan, int hinhAnh, int thoiGianYeuCau, String trangThai) {
        this.tenBaiTap = tenBaiTap;
        this.huongDan = huongDan;
        this.hinhAnh = hinhAnh;
        this.thoiGianYeuCau = thoiGianYeuCau;
        this.trangThai = trangThai;
    }

    public String getTenBaiTap() {
        return tenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        this.tenBaiTap = tenBaiTap;
    }

    public String getHuongDan() {
        return huongDan;
    }

    public void setHuongDan(String huongDan) {
        this.huongDan = huongDan;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getThoiGianYeuCau() {
        return thoiGianYeuCau;
    }

    public void setThoiGianYeuCau(int thoiGianYeuCau) {
        this.thoiGianYeuCau = thoiGianYeuCau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
