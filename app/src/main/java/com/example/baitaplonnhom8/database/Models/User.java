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
    public float getBMI(){
        float BMI = (float) (CANNANG/Math.pow(CHIEUCAO/100,2));
        return BMI;
    }
    public String getCondition(){
        float BMI = getBMI();
        if(BMI < 18.5){
            return "Nhẹ cân";
        }
        else if(BMI >= 18.5 && BMI <= 24.9) {
            return "Cân nặng bình thường";
        }else if(BMI >= 25 && BMI <= 29.9){
                return "Thừa cân";
            }else if (BMI >= 30){
                return "Béo phì độ 1";
        }else if(BMI >= 35 && BMI <= 39.9){
                return "Béo phì độ 2";
        }else{
                return "Béo phì độ 3";
        }


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
