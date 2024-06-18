package com.example.baitaplonnhom8;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.baitaplonnhom8.database.Models.User;

public class phienDangNhapUser {
    private static int MATK = 0;
    private static String USERNAME = "";
    private static Double CHIEUCAO = 0.0;
    private static Double CANNANG = 0.0;
    private static String EMAIL = "";
    private static String MATKHAU = "";
    public static void saveUserToPreferences(Context context,int MATK, String USERNAME, Double CHIEUCAO, Double CANNANG, String EMAIL, String MATKHAU){
        SharedPreferences sharedPreferences = context.getSharedPreferences("phienDangNhapUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("MATK", MATK);
        editor.putString("USERNAME", USERNAME);
        editor.putFloat("CHIEUCAO", (float) Float.valueOf(String.valueOf(CHIEUCAO)));
        editor.putFloat("CANNANG", (float) Float.valueOf(String.valueOf(CANNANG)));
        editor.putString("EMAIL", EMAIL);
        editor.putString("MATKHAU", MATKHAU);
        editor.apply();  // Lưu thay đổi

    }
    public static void saveUserToPreferences(Context context,User user){
        SharedPreferences sharedPreferences = context.getSharedPreferences("phienDangNhapUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("MATK", user.getMATK());
        editor.putString("USERNAME", user.getHOTEN());
        editor.putFloat("CHIEUCAO", user.getCHIEUCAO());
        editor.putFloat("CANNANG", user.getCANNANG());
        editor.putString("EMAIL", user.getEMAIL());
        editor.putString("MATKHAU", user.getMATKHAU());
        editor.apply();  // Lưu thay đổi

    }
    public static void changePassWord(Context context,String matkhau){
        SharedPreferences sharedPreferences = context.getSharedPreferences("phienDangNhapUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("MATKHAU", matkhau);
        editor.apply();  // Lưu thay đổi
    }
    public static User getUserFromPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("phienDangNhapUser", Context.MODE_PRIVATE);
        int matk = sharedPreferences.getInt("MATK", 0);
        String username = sharedPreferences.getString("USERNAME", "");
        float chieucao =Float.valueOf(String.valueOf( sharedPreferences.getFloat("CHIEUCAO", 0.0f)));
        float cannang =Float.valueOf(String.valueOf( sharedPreferences.getFloat("CANNANG", 0.0f)));
        String email = sharedPreferences.getString("EMAIL", "");
        String matkhau = sharedPreferences.getString("MATKHAU", "");
        return new User(matk,username,chieucao,cannang,email,matkhau);
    }

}
