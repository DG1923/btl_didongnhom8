package com.example.baitaplonnhom8.database.Models;

public class Task {
    private int MABT;
    private String TENBT;
    private String anhMinhHoa;
    private String HUONGDAN;
    private int THOIGIANYC;
    private int THOIGIANTHUCTE;
    private String TRANGTHAI;
    private int MAMH;

    public Task(int MABT, String TENBT, String ANHMINHHOA, String HUONGDAN, int THOIGIANYC, int THOIGIANTHUCTE, String TRANGTHAI, int MAMH) {
        this.MABT = MABT;
        this.TENBT = TENBT;
        this.anhMinhHoa=ANHMINHHOA;
        this.HUONGDAN = HUONGDAN;
        this.THOIGIANYC = THOIGIANYC;
        this.THOIGIANTHUCTE = THOIGIANTHUCTE;
        this.TRANGTHAI = TRANGTHAI;
        this.MAMH = MAMH;
    }

    public int getMABT() {
        return MABT;
    }

    public void setMABT(int MABT) {
        this.MABT = MABT;
    }

    public String getTENBT() {
        return TENBT;
    }

    public void setTENBT(String TENBT) {
        this.TENBT = TENBT;
    }

    // Getter and setter for ANHMINHHOA
    public String getAnhMinhHoa() {
        return anhMinhHoa;
    }

    public void setAnhMinhHoa(String anhMinhHoa) {
        this.anhMinhHoa = anhMinhHoa;
    }

    public String getHUONGDAN() {
        return HUONGDAN;
    }

    public void setHUONGDAN(String HUONGDAN) {
        this.HUONGDAN = HUONGDAN;
    }

    public int getTHOIGIANYC() {
        return THOIGIANYC;
    }

    public void setTHOIGIANYC(int THOIGIANYC) {
        this.THOIGIANYC = THOIGIANYC;
    }

    public int getTHOIGIANTHUCTE() {
        return THOIGIANTHUCTE;
    }

    public void setTHOIGIANTHUCTE(int THOIGIANTHUCTE) {
        this.THOIGIANTHUCTE = THOIGIANTHUCTE;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public int getMAMH() {
        return MAMH;
    }

    public void setMAMH(int MAMH) {
        this.MAMH = MAMH;
    }
}
