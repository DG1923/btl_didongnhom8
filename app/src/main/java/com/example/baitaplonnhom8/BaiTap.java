package com.example.baitaplonnhom8;

public class BaiTap {
    private int maBaiTap;
    private String tenBaiTap;
    private String huongDan;
    private int hinhAnh;
    private int thoiGianYeuCau;
    private int thoiGianThucTe;
    private String trangThai;

    public BaiTap(int maBaiTap, String tenBaiTap, String huongDan, int hinhAnh, int thoiGianYeuCau, int thoiGianThucTe, String trangThai) {
        this.maBaiTap = maBaiTap;
        this.tenBaiTap = tenBaiTap;
        this.huongDan = huongDan;
        this.hinhAnh = hinhAnh;
        this.thoiGianYeuCau = thoiGianYeuCau;
        this.thoiGianThucTe = thoiGianThucTe;
        this.trangThai = trangThai;
    }

    public BaiTap(int maBaiTap, String tenBaiTap, int thoiGianYeuCau, int thoiGianThucTe, String trangThai, int hinhAnh) {
    }

    public int getMaBaiTap() {
        return maBaiTap;
    }

    public void setMaBaiTap(int maBaiTap) {
        this.maBaiTap = maBaiTap;
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

    public int getThoiGianThucTe() {
        return thoiGianThucTe;
    }

    public void setThoiGianThucTe(int thoiGianThucTe) {
        this.thoiGianThucTe = thoiGianThucTe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
