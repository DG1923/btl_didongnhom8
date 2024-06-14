package com.example.baitaplonnhom8.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.baitaplonnhom8.database.Models.Task;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FitnessApp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table names
    public static final String DB_TAIKHOAN = "TAIKHOAN";
    public static final String DB_MONHOC = "MONHOC";
    public static final String DB_TAIKHOAN_MONHOC = "TAIKHOAN_MONHOC";
    public static final String DB_BAITAP = "BAITAP";

    // Column names for TAIKHOAN table
    public static final String DB_TAIKHOAN_MATK = "MATK";
    public static final String DB_TAIKHOAN_HOTEN = "HOTEN";
    public static final String DB_TAIKHOAN_CHIEUCAO = "CHIEUCAO";
    public static final String DB_TAIKHOAN_CANNANG = "CANNANG";
    public static final String DB_TAIKHOAN_EMAIL = "EMAIL";
    public static final String DB_TAIKHOAN_MATKHAU = "MATKHAU";

    // Column names for MONHOC table
    public static final String DB_MONHOC_MAMH = "MAMH";
    public static final String DB_MONHOC_TENMH = "TENMH";
    public static final String DB_MONHOC_ANHMH = "ANHMH";

    // Column names for TAIKHOAN_MONHOC table
    public static final String DB_TAIKHOAN_MONHOC_MAMH = "MAMH";
    public static final String DB_TAIKHOAN_MONHOC_MATK = "MATK";

    // Column names for BAITAP table
    public static final String DB_BAITAP_MABT = "MABT";
    public static final String DB_BAITAP_TENBT = "TENBT";
    public static final String DB_BAITAP_ANHMINHHOA = "ANHMINHHOA";
    public static final String DB_BAITAP_HUONGDAN = "HUONGDAN";
    public static final String DB_BAITAP_THOIGIANYC = "THOIGIANYC";
    public static final String DB_BAITAP_THOIGIANTHUCTE = "THOIGIANTHUCTE";
    public static final String DB_BAITAP_TRANGTHAI = "TRANGTHAI";
    public static final String DB_BAITAP_MAMH = "MAMH";

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
        Cursor cursor = db.rawQuery(query, new String[]{tableName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create TAIKHOAN table
        if (!isTableExists(db, DB_TAIKHOAN)) {
            String createTableTaiKhoan = "CREATE TABLE " + DB_TAIKHOAN + " (" +
                    DB_TAIKHOAN_MATK + " INTEGER PRIMARY KEY, " +
                    DB_TAIKHOAN_HOTEN + " TEXT, " +
                    DB_TAIKHOAN_CHIEUCAO + " REAL, " +
                    DB_TAIKHOAN_CANNANG + " REAL, " +
                    DB_TAIKHOAN_EMAIL + " TEXT, " +
                    DB_TAIKHOAN_MATKHAU + " TEXT)";
            db.execSQL(createTableTaiKhoan);
        }
        // Create MONHOC table
        if (!isTableExists(db, DB_MONHOC)) {
            String createTableMonHoc = "CREATE TABLE " + DB_MONHOC + " (" +
                    DB_MONHOC_MAMH + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DB_MONHOC_TENMH + " TEXT, " +
                    DB_MONHOC_ANHMH + " BLOB)";
            db.execSQL(createTableMonHoc);
        }

        // Create TAIKHOAN_MONHOC table
        if (!isTableExists(db, DB_TAIKHOAN_MONHOC)) {
            String createTableTaiKhoanMonHoc = "CREATE TABLE " + DB_TAIKHOAN_MONHOC + " (" +
                    DB_TAIKHOAN_MONHOC_MAMH + " INTEGER, " +
                    DB_TAIKHOAN_MONHOC_MATK + " INTEGER, " +
                    "FOREIGN KEY(" + DB_TAIKHOAN_MONHOC_MAMH + ") REFERENCES " + DB_MONHOC + "(" + DB_MONHOC_MAMH + "), " +
                    "FOREIGN KEY(" + DB_TAIKHOAN_MONHOC_MATK + ") REFERENCES " + DB_TAIKHOAN + "(" + DB_TAIKHOAN_MATK + "), " +
                    "PRIMARY KEY(" + DB_TAIKHOAN_MONHOC_MAMH + ", " + DB_TAIKHOAN_MONHOC_MATK + "))";
            db.execSQL(createTableTaiKhoanMonHoc);
        }

        // Create BAITAP table
        if (!isTableExists(db, DB_BAITAP)) {
            String createTableBaiTap = "CREATE TABLE " + DB_BAITAP + " (" +
                    DB_BAITAP_MABT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DB_BAITAP_TENBT + " TEXT, " +
                    DB_BAITAP_ANHMINHHOA + " TEXT, " +  // Changed to TEXT to store image path
                    DB_BAITAP_HUONGDAN + " TEXT, " +
                    DB_BAITAP_THOIGIANYC + " INTEGER, " +
                    DB_BAITAP_THOIGIANTHUCTE + " INTEGER, " +
                    DB_BAITAP_TRANGTHAI + " TEXT, " +
                    DB_BAITAP_MAMH + " INTEGER, " +
                    "FOREIGN KEY(" + DB_BAITAP_MAMH + ") REFERENCES " + DB_MONHOC + "(" + DB_MONHOC_MAMH + "))";
            db.execSQL(createTableBaiTap);
        }

        initialData(db);
        insertMonHocData(db);
        insertBaiTapData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TAIKHOAN_MONHOC);
        db.execSQL("DROP TABLE IF EXISTS " + DB_BAITAP);
        db.execSQL("DROP TABLE IF EXISTS " + DB_MONHOC);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TAIKHOAN);
        onCreate(db);
    }

    // Initial data method
    private void initialData(SQLiteDatabase db) {
        // Insert sample data into TAIKHOAN table
        ContentValues values = new ContentValues();
        values.put(DB_TAIKHOAN_HOTEN, "Man Ba Hao");
        values.put(DB_TAIKHOAN_CHIEUCAO, 173);
        values.put(DB_TAIKHOAN_CANNANG, 85);
        values.put(DB_TAIKHOAN_EMAIL, "hao@example.com");
        values.put(DB_TAIKHOAN_MATKHAU, "password123");
        db.insert(DB_TAIKHOAN, null, values);
    }

    public void insertMonHoc(SQLiteDatabase db, String tenMH, String anhMH) {
        ContentValues values = new ContentValues();
        values.put(DB_MONHOC_TENMH, tenMH);
        values.put(DB_MONHOC_ANHMH, anhMH);
        db.insert(DB_MONHOC, null, values);
    }

    public void insertBaiTap(SQLiteDatabase db, String tenBT, String huongDan, int thoiGianYC, int thoiGianThucTe, String trangThai, int maMH, String anhMinhHoa) {
        ContentValues values = new ContentValues();
        values.put(DB_BAITAP_TENBT, tenBT);
        values.put(DB_BAITAP_HUONGDAN, huongDan);
        values.put(DB_BAITAP_THOIGIANYC, thoiGianYC);
        values.put(DB_BAITAP_THOIGIANTHUCTE, thoiGianThucTe);
        values.put(DB_BAITAP_TRANGTHAI, trangThai);
        values.put(DB_BAITAP_MAMH, maMH);
        values.put(DB_BAITAP_ANHMINHHOA, anhMinhHoa); // Changed to store image path
        db.insert(DB_BAITAP, null, values);
    }

    private void insertMonHocData(SQLiteDatabase db) {
        // Sample data for MONHOC table
        insertMonHoc(db, "Hít Đất", "AnhBaiTap/Running.png");
        insertMonHoc(db, "Hít Xà", "AnhBaiTap/Running.png");
        insertMonHoc(db, "Plank", "AnhBaiTap/Running.png");
        insertMonHoc(db, "Gập Bụng", "AnhBaiTap/Running.png");
        insertMonHoc(db, "Squat", "AnhBaiTap/Running.png");
    }

    private void insertBaiTapData(SQLiteDatabase db) {
        // Insert provided exercise data
        String[] exercises = {
                "('Cardio', 'AnhBaiTap/Running.png', 'Do cardio exercises. Engage in activities like running, cycling, or swimming to increase heart rate and burn calories.', 45, 0, 'Incomplete', 6)",
                "('HIIT', 'AnhBaiTap/Running.png', 'Do high-intensity interval training. Alternate between short bursts of intense exercise and recovery periods.', 30, 0, 'Incomplete', 6)",
                "('Cycling', 'AnhBaiTap/Running.png', 'Go cycling. Ride a bike outdoors or use a stationary bike to improve cardiovascular fitness and burn fat.', 60, 0, 'Incomplete', 6)",
                "('Jump Rope', 'AnhBaiTap/Running.png', 'Jump rope. Perform continuous jumps, focusing on maintaining a steady rhythm and increasing intensity.', 30, 0, 'Incomplete', 6)",
                "('Running', 'AnhBaiTap/Running.png', 'Go for a run. Maintain a steady pace, aiming to increase distance and speed over time.', 45, 0, 'Incomplete', 6)",
                "('Swimming', 'AnhBaiTap/Running.png', 'Go swimming. Engage in various strokes to work different muscle groups and improve cardiovascular fitness.', 45, 0, 'Incomplete', 6)",
                "('Rowing', 'AnhBaiTap/Running.png', 'Use a rowing machine. Perform continuous rowing motions, focusing on full-body engagement and intensity.', 45, 0, 'Incomplete', 6)",
                "('Stair Climbing', 'AnhBaiTap/Running.png', 'Climb stairs. Use a stair climber or actual stairs to increase heart rate and engage lower body muscles.', 30, 0, 'Incomplete', 6)",
                "('Dance Workout', 'AnhBaiTap/Running.png', 'Do a dance workout. Follow a dance routine to improve cardiovascular fitness and burn calories.', 45, 0, 'Incomplete', 6)",
                "('Boxing', 'AnhBaiTap/Running.png', 'Do a boxing workout. Perform punches, jabs, and footwork drills to increase heart rate and burn fat.', 30, 0, 'Incomplete', 6)",
                "('Circuit Training', 'AnhBaiTap/Running.png', 'Do circuit training. Rotate through various exercises with minimal rest to maintain an elevated heart rate.', 45, 0, 'Incomplete', 6)",
                "('Kickboxing', 'AnhBaiTap/Running.png', 'Do a kickboxing workout. Perform kicks, punches, and combinations to improve cardiovascular fitness and burn fat.', 45, 0, 'Incomplete', 6)",
                "('Aerobics', 'AnhBaiTap/Running.png', 'Do an aerobics workout. Follow a choreographed routine to increase heart rate and burn calories.', 45, 0, 'Incomplete', 6)",
                "('Elliptical Training', 'AnhBaiTap/Running.png', 'Use an elliptical machine. Engage in a low-impact cardiovascular workout to burn calories.', 45, 0, 'Incomplete', 6)",
                "('Treadmill Workout', 'AnhBaiTap/Running.png', 'Use a treadmill. Alternate between walking, jogging, and running to maintain an elevated heart rate.', 45, 0, 'Incomplete', 6)",
                "('Step Aerobics', 'AnhBaiTap/Running.png', 'Do step aerobics. Follow a choreographed routine using a step platform to increase intensity and burn calories.', 45, 0, 'Incomplete', 6)",
                "('Zumba', 'AnhBaiTap/Running.png', 'Do a Zumba workout. Follow a dance-based fitness routine to improve cardiovascular fitness and burn fat.', 45, 0, 'Incomplete', 6)",
                "('Bodyweight Exercises', 'AnhBaiTap/Running.png', 'Do bodyweight exercises. Perform movements like burpees, squats, and lunges to increase intensity and burn fat.', 30, 0, 'Incomplete', 6)",
                "('Cool Down', 'AnhBaiTap/Running.png', 'Cool down and stretch after a cardio workout. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 6)",
                "('Foam Rolling', 'AnhBaiTap/Running.png', 'Use a foam roller. Roll over major muscle groups to release tension and aid in recovery.', 30, 0, 'Incomplete', 6)",
                "('Yoga', 'AnhBaiTap/yoga.png', 'Do yoga. Follow a yoga routine to improve flexibility, balance, and mental relaxation.', 45, 0, 'Incomplete', 7)",
                "('Stretching', 'AnhBaiTap/yoga.png', 'Do stretching exercises. Focus on major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 7)",
                "('Light Jogging', 'AnhBaiTap/yoga.png', 'Go for a light jog. Maintain a slow, steady pace to keep your body active without overexertion.', 30, 0, 'Incomplete', 7)",
                "('Pilates', 'AnhBaiTap/yoga.png', 'Do Pilates. Follow a routine to improve core strength, flexibility, and overall body control.', 45, 0, 'Incomplete', 7)",
                "('Walking', 'AnhBaiTap/yoga.png', 'Go for a walk. Maintain a steady pace to keep your body active and improve cardiovascular health.', 30, 0, 'Incomplete', 7)",
                "('Tai Chi', 'AnhBaiTap/yoga.png', 'Practice Tai Chi. Follow a series of slow, controlled movements to improve balance and mental relaxation.', 45, 0, 'Incomplete', 7)",
                "('Meditation', 'AnhBaiTap/yoga.png', 'Practice meditation. Focus on breathing and relaxation techniques to improve mental clarity and reduce stress.', 30, 0, 'Incomplete', 7)",
                "('Balance Training', 'AnhBaiTap/yoga.png', 'Do balance training exercises. Focus on stability and coordination to maintain physical health.', 30, 0, 'Incomplete', 7)",
                "('Core Exercises', 'AnhBaiTap/yoga.png', 'Do core exercises. Perform movements like planks and crunches to strengthen your core muscles.', 30, 0, 'Incomplete', 7)",
                "('Breathing Exercises', 'AnhBaiTap/yoga.png', 'Practice breathing exercises. Focus on deep, controlled breaths to improve lung capacity and relaxation.', 30, 0, 'Incomplete', 7)",
                "('Foam Rolling', 'AnhBaiTap/yoga.png', 'Use a foam roller. Roll over major muscle groups to release tension and aid in recovery.', 30, 0, 'Incomplete', 7)",
                "('Low-Impact Cardio', 'AnhBaiTap/yoga.png', 'Do low-impact cardio exercises. Engage in activities like cycling or swimming to keep active without strain.', 45, 0, 'Incomplete', 7)",
                "('Resistance Band Exercises', 'AnhBaiTap/yoga.png', 'Use resistance bands. Perform exercises to strengthen muscles while maintaining low impact.', 30, 0, 'Incomplete', 7)",
                "('Dance Workout', 'AnhBaiTap/yoga.png', 'Do a dance workout. Follow a light dance routine to keep active and have fun.', 45, 0, 'Incomplete', 7)",
                "('Outdoor Activities', 'AnhBaiTap/yoga.png', 'Engage in outdoor activities. Enjoy hiking, gardening, or other low-intensity activities.', 60, 0, 'Incomplete', 7)",
                "('Group Exercise Class', 'AnhBaiTap/yoga.png', 'Join a group exercise class. Participate in a class designed for maintaining fitness and social interaction.', 45, 0, 'Incomplete', 7)",
                "('Swimming', 'AnhBaiTap/yoga.png', 'Go swimming. Enjoy a low-impact full-body workout in the water.', 45, 0, 'Incomplete', 7)",
                "('Cycling', 'AnhBaiTap/yoga.png', 'Go cycling. Ride a bike outdoors or use a stationary bike for a low-impact workout.', 45, 0, 'Incomplete', 7)",
                "('Cool Down', 'AnhBaiTap/yoga.png', 'Cool down and stretch after exercise. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 7)",
                "('Mental Training', 'AnhBaiTap/yoga.png', 'Work on mental toughness. Practice focusing techniques and strategies to stay calm and motivated.', 30, 0, 'Incomplete', 7)"
        };

        for (String exercise : exercises) {
            String insertQuery = "INSERT INTO " + DB_BAITAP + " (" + DB_BAITAP_TENBT + ", " + DB_BAITAP_ANHMINHHOA + ", " +
                    DB_BAITAP_HUONGDAN + ", " + DB_BAITAP_THOIGIANYC + ", " + DB_BAITAP_THOIGIANTHUCTE + ", " +
                    DB_BAITAP_TRANGTHAI + ", " + DB_BAITAP_MAMH + ") VALUES " + exercise + ";";
            db.execSQL(insertQuery);
        }

    }
    public long signUp(int id, String hoten, float chieucao, float cannang, String email, String matkhau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_TAIKHOAN_MATK, id);
        values.put(DB_TAIKHOAN_HOTEN, hoten);
        values.put(DB_TAIKHOAN_CHIEUCAO, chieucao);
        values.put(DB_TAIKHOAN_CANNANG, cannang);
        values.put(DB_TAIKHOAN_EMAIL, email);
        values.put(DB_TAIKHOAN_MATKHAU, matkhau);
        long newRowId = db.insert(DB_TAIKHOAN, null, values);
        db.close(); // Closing database connection
        return newRowId;
    }
    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DB_TAIKHOAN_MATK
        };

        // Filter results WHERE "email" = 'inputEmail' AND "password" = 'inputPassword'
        String selection = DB_TAIKHOAN_EMAIL + " = ? AND " + DB_TAIKHOAN_MATKHAU + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                DB_TAIKHOAN,   // The table to query
                projection,             // The array of columns to return (null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                    // The sort order
        );

        boolean isLoggedIn = cursor.moveToFirst();
        cursor.close();
        db.close(); // Closing database connection
        return isLoggedIn;
    }

    public List<Task> getTasksByStatus(String status) {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DB_BAITAP_MABT,
                DB_BAITAP_TENBT,
                DB_BAITAP_ANHMINHHOA,
                DB_BAITAP_HUONGDAN,
                DB_BAITAP_THOIGIANYC,
                DB_BAITAP_THOIGIANTHUCTE,
                DB_BAITAP_TRANGTHAI,
                DB_BAITAP_MAMH
        };

        String selection = DB_BAITAP_TRANGTHAI + " = ?";
        String[] selectionArgs = {status};

        Cursor cursor = db.query(
                DB_BAITAP,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int taskId = cursor.getInt(cursor.getColumnIndexOrThrow(DB_BAITAP_MABT));
            String taskName = cursor.getString(cursor.getColumnIndexOrThrow(DB_BAITAP_TENBT));
            String anhMinhHoa = cursor.getString(cursor.getColumnIndexOrThrow(DB_BAITAP_ANHMINHHOA));
            String huongDan = cursor.getString(cursor.getColumnIndexOrThrow(DB_BAITAP_HUONGDAN));
            int thoiGianYC = cursor.getInt(cursor.getColumnIndexOrThrow(DB_BAITAP_THOIGIANYC));
            int thoiGianThucTe = cursor.getInt(cursor.getColumnIndexOrThrow(DB_BAITAP_THOIGIANTHUCTE));
            String taskStatus = cursor.getString(cursor.getColumnIndexOrThrow(DB_BAITAP_TRANGTHAI));
            int maMH = cursor.getInt(cursor.getColumnIndexOrThrow(DB_BAITAP_MAMH));

            Task task = new Task(taskId, taskName, anhMinhHoa, huongDan, thoiGianYC, thoiGianThucTe, taskStatus, maMH);
            taskList.add(task);
        }
        cursor.close();
        db.close();
        return taskList;
    }
    public String getSubjectName(int subjectId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = { DB_MONHOC_TENMH }; // Assuming DB_MONHOC_TENMH is the column name for subject name
        String selection = DB_MONHOC_MAMH + " = ?";
        String[] selectionArgs = { String.valueOf(subjectId) };

        Cursor cursor = db.query(
                DB_MONHOC,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String subjectName = null;
        if (cursor.moveToFirst()) {
            subjectName = cursor.getString(cursor.getColumnIndexOrThrow(DB_MONHOC_TENMH));
        }

        cursor.close();
        db.close();

        return subjectName;
    }


}
