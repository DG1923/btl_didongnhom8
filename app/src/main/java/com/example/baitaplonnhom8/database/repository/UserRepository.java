package com.example.baitaplonnhom8.database.repository;

import com.example.baitaplonnhom8.database.DatabaseHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.example.baitaplonnhom8.database.Models.User;
public class UserRepository extends BaseRepository<User> {
    public UserRepository(DatabaseHelper dbHelper) {
        super(dbHelper, "users");
    }

    @Override
    protected User getItemFromCursor(Cursor cursor) {
        int MATK = cursor.getInt(cursor.getColumnIndexOrThrow("MATK"));
        String HOTEN = cursor.getString(cursor.getColumnIndexOrThrow("HOTEN"));
        float CHIEUCAO = cursor.getFloat(cursor.getColumnIndexOrThrow("CHIEUCAO"));
        float CANNANG = cursor.getFloat(cursor.getColumnIndexOrThrow("CANNANG"));
        String EMAIL = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));
        String MATKHAU = cursor.getString(cursor.getColumnIndexOrThrow("MATKHAU"));

        return new User(MATK, HOTEN, CHIEUCAO, CANNANG, EMAIL, MATKHAU);
    }

    @Override
    protected ContentValues getContentValues(User item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATK", item.getMATK());
        contentValues.put("HOTEN", item.getHOTEN());
        contentValues.put("CHIEUCAO", item.getCHIEUCAO());
        contentValues.put("CANNANG", item.getCANNANG());
        contentValues.put("EMAIL", item.getEMAIL());
        contentValues.put("MATKHAU", item.getMATKHAU());
        return contentValues;
    }
    public boolean login(String email, String password) {
        return dbHelper.login(email, password);
    }

    public long signUp(User user) {
        return dbHelper.signUp( user.getMATK(),user.getHOTEN(), user.getCHIEUCAO(), user.getCANNANG(),
                user.getEMAIL(), user.getMATKHAU());
    }
}
