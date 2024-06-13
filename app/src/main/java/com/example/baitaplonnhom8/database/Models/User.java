package com.example.baitaplonnhom8.database.Models;

public class User {
    private int MATK;
    private String HOTEN;
    private float CHIEUCAO;
    private float CANNANG;
    private String EMAIL;
    private String MATKHAU;

    public User(int MATK, String HOTEN, float CHIEUCAO, float CANNANG, String EMAIL, String MATKHAU) {
        this.MATK = MATK;
        this.HOTEN = HOTEN;
        this.CHIEUCAO = CHIEUCAO;
        this.CANNANG = CANNANG;
        this.EMAIL = EMAIL;
        this.MATKHAU = MATKHAU;
    }

    public int getMATK() {
        return MATK;
    }

    public void setMATK(int MATK) {
        this.MATK = MATK;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public float getCHIEUCAO() {
        return CHIEUCAO;
    }

    public void setCHIEUCAO(float CHIEUCAO) {
        this.CHIEUCAO = CHIEUCAO;
    }

    public float getCANNANG() {
        return CANNANG;
    }

    public void setCANNANG(float CANNANG) {
        this.CANNANG = CANNANG;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }
}
