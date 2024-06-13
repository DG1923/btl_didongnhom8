package com.example.baitaplonnhom8.database.Models;

public class Subject_User {
    private int MAMH;
    private int MATK;

    public Subject_User(int MAMH, int MATK) {
        this.MAMH = MAMH;
        this.MATK = MATK;
    }

    public int getMAMH() {
        return MAMH;
    }

    public void setMAMH(int MAMH) {
        this.MAMH = MAMH;
    }

    public int getMATK() {
        return MATK;
    }

    public void setMATK(int MATK) {
        this.MATK = MATK;
    }
}
