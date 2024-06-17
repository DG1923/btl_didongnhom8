package com.example.baitaplonnhom8.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.baitaplonnhom8.Exercise;
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
                    DB_BAITAP_ANHMINHHOA + " TEXT, " +
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
        insertMonHoc(db, "Badminton", "AnhMonHoc/badminton.png");
        insertMonHoc(db, "Football", "AnhMonHoc/football.png");
        insertMonHoc(db, "Volleyball", "AnhMonHoc/volleyball.png");
        insertMonHoc(db, "Tenis", "AnhMonHoc/tenis.png");
        insertMonHoc(db, "Aerobic", "AnhMonHoc/aerobic.png");
        insertMonHoc(db, "Bài tập giảm mơ", "AnhMonHoc/GiamMo.png");
        insertMonHoc(db, "Bài tập tăng cơ", "AnhMonHoc/TangCo.png");
        insertMonHoc(db, "Bài tập duy trì thể trạng", "AnhMonHoc/running.png");
        insertMonHoc(db, "Fast Warmup", "AnhMonHoc/fastwarmup.png");
    }
    public String getTenMonHocByMaMH(int maMH){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+DB_MONHOC_TENMH+
                        " FROM "+DB_MONHOC+
                        " WHERE "+DB_MONHOC+"."+DB_MONHOC_TENMH+" = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maMH)});
        return cursor.getString(cursor.getColumnIndexOrThrow(DB_MONHOC_TENMH));
    }

    private void insertBaiTapData(SQLiteDatabase db) {
        // Insert provided exercise data
        String[] exercises = {
                "('Cardio', 'AnhBaiTap/Cardio.png', 'Do cardio exercises. Engage in activities like running, cycling, or swimming to increase heart rate and burn calories.', 45, 0, 'Incomplete', 6)",
                "('HIIT', 'AnhBaiTap/HIIT.png', 'Do high-intensity interval training. Alternate between short bursts of intense exercise and recovery periods.', 30, 0, 'Incomplete', 6)",
                "('Cycling', 'AnhBaiTap/Cycling.png', 'Go cycling. Ride a bike outdoors or use a stationary bike to improve cardiovascular fitness and burn fat.', 60, 0, 'Incomplete', 6)",
                "('Jump Rope', 'AnhBaiTap/Jump Rope.png', 'Jump rope. Perform continuous jumps, focusing on maintaining a steady rhythm and increasing intensity.', 30, 0, 'Incomplete', 6)",
                "('Running', 'AnhBaiTap/Running.png', 'Go for a run. Maintain a steady pace, aiming to increase distance and speed over time.', 45, 0, 'Incomplete', 6)",
                "('Swimming', 'AnhBaiTap/Swimming.png', 'Go swimming. Engage in various strokes to work different muscle groups and improve cardiovascular fitness.', 45, 0, 'Incomplete', 6)",
                "('Rowing', 'AnhBaiTap/Rowing.png', 'Use a rowing machine. Perform continuous rowing motions, focusing on full-body engagement and intensity.', 45, 0, 'Incomplete', 6)",
                "('Stair Climbing', 'AnhBaiTap/Stair Climbing.png', 'Climb stairs. Use a stair climber or actual stairs to increase heart rate and engage lower body muscles.', 30, 0, 'Incomplete', 6)",
                "('Bench Press', 'AnhBaiTap/Bench Press.png', 'Do yoga. Follow a yoga routine to improve flexibility, balance, and mental relaxation.', 45, 0, 'Incomplete', 7)",
                "('Dumbbell Pullover', 'AnhBaiTap/Dumbbell Pullover.png', 'Do stretching exercises. Focus on major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 7)",
                "('Bent Over Barbell Row', 'AnhBaiTap/Bent Over Barbell Row.png', 'Go for a light jog. Maintain a slow, steady pace to keep your body active without overexertion.', 30, 0, 'Incomplete', 7)",
                "('Pull Up', 'AnhBaiTap/Pull Up.png', 'Do Pilates. Follow a routine to improve core strength, flexibility, and overall body control.', 45, 0, 'Incomplete', 7)",
                "('Squat ', 'AnhBaiTap/Squat.png', 'Go for a walk. Maintain a steady pace to keep your body active and improve cardiovascular health.', 30, 0, 'Incomplete', 7)",
                "('Leg Press', 'AnhBaiTap/Leg Press.png', 'Practice Tai Chi. Follow a series of slow, controlled movements to improve balance and mental relaxation.', 45, 0, 'Incomplete', 7)",
                "('Seated Dumbbell Shoulder Press', 'AnhBaiTap/Seated Dumbbell Shoulder Press.png', 'Practice meditation. Focus on breathing and relaxation techniques to improve mental clarity and reduce stress.', 30, 0, 'Incomplete', 7)",
                "('Upright Row','AnhBaiTap/Upright Row.png', 'Do balance training exercises. Focus on stability and coordination to maintain physical health.', 30, 0, 'Incomplete', 7)",
                "('Dip', 'AnhBaiTap/Dip.png', 'Do core exercises. Perform movements like planks and crunches to strengthen your core muscles.', 30, 0, 'Incomplete', 7)",
                "('Push-ups', 'AnhBaiTap/PushUps.png', 'Perform push-ups to strengthen your chest, shoulders, and triceps. Maintain a straight line from head to heels.', 30, 0, 'Incomplete', 8)",
                "('Squats', 'AnhBaiTap/Squats.png', 'Do squats to target your lower body muscles, including quads, hamstrings, and glutes. Keep your back straight.', 40, 0, 'Incomplete', 8)",
                "('Plank', 'AnhBaiTap/Plank.png', 'Hold a plank position to work on your core strength. Ensure your body forms a straight line from head to heels.', 60, 0, 'Incomplete', 8)",
                "('Lunges', 'AnhBaiTap/Lunges.png', 'Perform lunges to work on your thighs and glutes. Step forward with one leg and lower your hips until both knees are bent.', 35, 0, 'Incomplete', 8)",
                "('Burpees', 'AnhBaiTap/Burpees.png', 'Do burpees for a full-body workout that increases strength and cardio fitness. Jump, squat, and push-up in one motion.', 50, 0, 'Incomplete', 8)",
                "('Mountain Climbers', 'AnhBaiTap/MountainClimbers.png', 'Perform mountain climbers to engage your core and improve cardiovascular fitness. Bring knees to chest alternately.', 45, 0, 'Incomplete', 8)",
                "('Bicycle Crunches', 'AnhBaiTap/BicycleCrunches.png', 'Do bicycle crunches to work on your abs and obliques. Touch elbow to opposite knee while extending the other leg.', 30, 0, 'Incomplete', 8)",
                "('Leg Raises', 'AnhBaiTap/LegRaises.png', 'Perform leg raises to strengthen your lower abs. Lift your legs off the ground while keeping them straight.', 40, 0, 'Incomplete', 8)",
                "('Jumping Jacks', 'AnhBaiTap/JumpingJacks.png', 'Do jumping jacks to increase your heart rate and work on your entire body. Jump while spreading your arms and legs.', 20, 0, 'Incomplete', 8)",
                "('Side Plank', 'AnhBaiTap/SidePlank.png', 'Hold a side plank position to target your obliques and improve core stability. Keep your body in a straight line.', 50, 0, 'Incomplete', 8)",
                "('T-ROTATIONS', 'AnhBaiTap/T-ROTATIONS.png', 'Hướng dẫn thực hiện bài tập T-ROTATIONS -OR- SPIDERMAN LUNGES', 5, 0, 'Incomplete', 9)",
                "('WALKING KNEE HUGS', 'AnhBaiTap/WALKING KNEE HUGS.png', 'Hướng dẫn thực hiện bài tập SUPINE -OR- WALKING KNEE HUGS', 5, 0, 'Incomplete', 9)",
                "('STEP JACK -OR- JUMPING JACK', 'AnhBaiTap/stepjack.png', 'Hướng dẫn thực hiện bài tập STEP JACK -OR- JUMPING JACK', 5, 0, 'Incomplete', 9)",
                "('MARCH OR JOG IN PLACE', 'AnhBaiTap/MARCH.png', 'Hướng dẫn thực hiện bài tập MARCH -OR- JOG IN PLACE', 5 ,0, 'Incomplete', 9)",
                "('LEG SWINGS', 'AnhBaiTap/LEG SWINGS.png', 'Hướng dẫn thực hiện bài tập LEG SWINGS', 5, 0, 'Incomplete', 9)",
                "('HIP CIRCLES', 'AnhBaiTap/HIP CIRCLES.png', 'Hướng dẫn thực hiện bài tập HIP CIRCLES', 5, 0, 'Incomplete', 9)",
                "('ARM CIRCLES', 'AnhBaiTap/ARM CIRCLES.png', 'Hướng dẫn thực hiện bài tập ARM CIRCLES', 5, 0, 'Incomplete', 9)",
        };

        for (String exercise : exercises) {
            String insertQuery = "INSERT INTO " + DB_BAITAP + " (" + DB_BAITAP_TENBT + ", " + DB_BAITAP_ANHMINHHOA + ", " +
                    DB_BAITAP_HUONGDAN + ", " + DB_BAITAP_THOIGIANYC + ", " + DB_BAITAP_THOIGIANTHUCTE + ", " +
                    DB_BAITAP_TRANGTHAI + ", " + DB_BAITAP_MAMH + ") VALUES " + exercise + ";";
            db.execSQL(insertQuery);
        }
    }
    //Lấy bài tập qua mã môn học
    public Cursor getBaiTapByMaMH(int MaMH){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * " +
                "FROM BAITAP " +
                "WHERE BAITAP.MAMH = ? AND BAITAP.TRANGTHAI = 'Incomplete'";
        return db.rawQuery(query, new String[]{String.valueOf(MaMH)});
    }
    public Exercise getBaiTapByMaBT(int maBT,int maMH){
        String query = "";
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        if(maBT == -1 && maMH != 1){
            query= query = "SELECT * FROM " + DB_BAITAP + " WHERE " + DB_BAITAP_MAMH + " = ? ORDER BY " + DB_BAITAP_MABT + " ASC LIMIT 1";
            cursor = db.rawQuery(query, new String[]{String.valueOf(maMH)});

        }else if(maBT !=1){
            query= "SELECT * FROM BAITAP WHERE BAITAP.MABT = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(maBT)});

        }

        Exercise exercise = null;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String img = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_ANHMINHHOA));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TENBT));
                    int category = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_MAMH));
                    int timerequire = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANYC));
                    int timereality = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANYC));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_HUONGDAN));
                    exercise = new Exercise(img, name, timerequire, category, timereality, description);
                }
            } finally {
                cursor.close();
            }
        }
        db.close();
        return exercise;
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
    public Cursor getMonHocByMaMH(int maMH) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DB_MONHOC + " WHERE " + DB_MONHOC_MAMH + " = ?", new String[]{String.valueOf(maMH)});
    }
}
