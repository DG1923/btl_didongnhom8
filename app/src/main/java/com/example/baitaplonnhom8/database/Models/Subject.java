package com.example.baitaplonnhom8.database.Models;

public class Subject {
    private int MAMH;
    private String TENMH;
    private String ANHMH;

    public Subject(int MAMH, String TENMH, String ANHMH) {
        this.MAMH = MAMH;
        this.TENMH = TENMH;
        this.ANHMH = ANHMH;
    }

    public int getMAMH() {
        return MAMH;
    }

    public void setMAMH(int MAMH) {
        this.MAMH = MAMH;
    }

    public String getTENMH() {
        return TENMH;
    }

    public void setTENMH(String TENMH) {
        this.TENMH = TENMH;
    }

    public String getANHMH() {
        return ANHMH;
    }

    public void setANHMH(String ANHMH) {
        this.ANHMH = ANHMH;
    }
}


