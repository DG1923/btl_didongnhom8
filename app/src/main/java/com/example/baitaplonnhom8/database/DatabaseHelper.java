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
        insertMonHoc(db, "Cầu lông", "AnhMonHoc/badminton.png");
        insertMonHoc(db, "Bóng đá", "AnhMonHoc/football.png");
        insertMonHoc(db, "Bóng chuyền", "AnhMonHoc/volleyball.png");
        insertMonHoc(db, "Tenis", "AnhMonHoc/tenis.png");
        insertMonHoc(db, "Aerobic", "AnhMonHoc/aerobic.png");
        insertMonHoc(db, "Bài tập giảm mỡ", "AnhMonHoc/GiamMo.png");
        insertMonHoc(db, "Bài tập tăng cơ", "AnhMonHoc/TangCo.png");
        insertMonHoc(db, "Bài tập duy trì thể trạng", "AnhMonHoc/running.png");
        insertMonHoc(db, "Fast Warmup", "AnhMonHoc/fastwarmup.png");
    }

    private void insertBaiTapData(SQLiteDatabase db) {
        // Insert provided exercise data
        String[] exercises = {
                // Cầu lông
                "('Tập Bump', 'AnhBaiTap/Bump Drill.png', 'Luyện tập đỡ cầu. Đứng với đầu gối uốn cong, tay duỗi thẳng. Tập trung sử dụng cẳng tay để điều khiển cầu lên trên.', 30, 0, 'Incomplete', 2)",
                "('Tập Đập Cầu', 'AnhBaiTap/Bump Drill.png', 'Luyện tập đập cầu. Tiến đến lưới, nhảy lên và đập cầu bằng tay mở, nhắm vào sân đối phương.', 30, 0, 'Incomplete', 2)",
                "('Tập Phát Cầu', 'AnhBaiTap/Bump Drill.png', 'Luyện tập phát cầu. Đứng sau vạch cuối sân, tung cầu lên không và đập nó bằng tay để phát cầu qua lưới.', 30, 0, 'Incomplete', 2)",
                "('Tập Chắn Cầu', 'AnhBaiTap/Bump Drill.png', 'Luyện tập chắn cầu tại lưới. Nhảy lên với tay duỗi thẳng, canh thời gian để chắn cú đập của đối phương.', 30, 0, 'Incomplete', 2)",
                "('Tập Chuyền Cầu', 'AnhBaiTap/Bump Drill.png', 'Luyện tập chuyền cầu. Sử dụng đầu ngón tay để đẩy cầu lên trên, nhắm đến người đập cầu.', 30, 0, 'Incomplete', 2)",
                "('Tập Đào Cầu', 'AnhBaiTap/Bump Drill.png', 'Luyện tập đào cầu. Giữ người thấp, sử dụng cẳng tay để ngăn cầu chạm đất sau cú tấn công của đối phương.', 30, 0, 'Incomplete', 2)",
                "('Tập Di Chuyển Chân', 'AnhBaiTap/Bump Drill.png', 'Luyện tập di chuyển chân. Di chuyển nhanh và hiệu quả quanh sân, duy trì thăng bằng và kiểm soát.', 30, 0, 'Incomplete', 2)",
                "('Tập Giao Tiếp Đội', 'AnhBaiTap/Bump Drill.png', 'Luyện tập giao tiếp đội. Gọi các chiến thuật và vị trí để đảm bảo sự phối hợp của đội.', 30, 0, 'Incomplete', 2)",
                "('Điều Kiện Thể Lực', 'AnhBaiTap/Bump Drill.png', 'Cải thiện điều kiện thể lực tổng quát. Tập trung vào sức bền, sức mạnh và các bài tập linh hoạt được điều chỉnh cho cầu lông.', 45, 0, 'Incomplete', 2)",
                "('Tập Tăng Tốc', 'AnhBaiTap/Bump Drill.png', 'Cải thiện tốc độ. Thực hiện các bài tập chạy nước rút và nhanh nhẹn để tăng thời gian phản ứng trên sân.', 30, 0, 'Incomplete', 2)",
                "('Tập Sức Mạnh', 'AnhBaiTap/Bump Drill.png', 'Tăng cường cơ bắp sử dụng trong cầu lông. Bao gồm các bài tập như squats, lunges và các bài tập cho phần trên cơ thể.', 45, 0, 'Incomplete', 2)",
                "('Tập Linh Hoạt', 'AnhBaiTap/Bump Drill.png', 'Cải thiện sự linh hoạt. Kéo giãn các nhóm cơ chính để tăng phạm vi chuyển động và ngăn ngừa chấn thương.', 30, 0, 'Incomplete', 2)",
                "('Mô Phỏng Trận Đấu', 'AnhBaiTap/Bump Drill.png', 'Mô phỏng một trận đấu thật. Luyện tập các tình huống giống như trận đấu để cải thiện ra quyết định và làm việc nhóm dưới áp lực.', 60, 0, 'Incomplete', 2)",
                "('Tập Phát Cầu Chính Xác', 'AnhBaiTap/Bump Drill.png', 'Luyện tập phát cầu chính xác. Nhắm các mục tiêu cụ thể trên sân đối phương khi phát cầu.', 30, 0, 'Incomplete', 2)",
                "('Tập Phòng Thủ', 'AnhBaiTap/Bump Drill.png', 'Luyện tập kỹ năng phòng thủ. Tập trung vào vị trí, chắn cầu và đào cầu để ngăn đối phương ghi điểm.', 30, 0, 'Incomplete', 2)",
                "('Tập Tấn Công', 'AnhBaiTap/Bump Drill.png', 'Luyện tập kỹ năng tấn công. Tập trung vào đập cầu, chuyền 2 và phát cầu để ghi điểm trước đối phương.', 30, 0, 'Incomplete', 2)",
                "('Tập Thời Gian Phản Xạ', 'AnhBaiTap/Bump Drill.png', 'Cải thiện thời gian phản xạ. Thực hiện các bài tập yêu cầu phản ứng nhanh với các kích thích khác nhau.', 30, 0, 'Incomplete', 2)",
                "('Thảo Luận Chiến Thuật Đội', 'AnhBaiTap/Bump Drill.png', 'Thảo luận và luyện tập chiến thuật đội. Lên kế hoạch các pha bóng và vị trí để tối đa hiệu quả.', 45, 0, 'Incomplete', 2)",
                "('Tập Sức Bền', 'AnhBaiTap/Bump Drill.png', 'Xây dựng sức bền. Thực hiện các bài tập tim mạch để tăng sức bền cho các trận đấu dài.', 45, 0, 'Incomplete', 2)",
                "('Hạ Nhiệt', 'AnhBaiTap/Bump Drill.png', 'Hạ nhiệt và kéo giãn sau buổi tập. Thực hiện kéo giãn nhẹ nhàng để giúp phục hồi cơ bắp và tăng linh hoạt.', 20, 0, 'Incomplete', 2)",
                // Bóng đá
                "('Tập Dẫn Bóng', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập dẫn bóng. Sử dụng nón để tạo lộ trình, di chuyển bóng bằng cả hai chân, duy trì kiểm soát.', 30, 0, 'Incomplete', 2)",
                "('Tập Sút Bóng', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập sút bóng. Nhắm vào các mục tiêu khác nhau trong khung thành, tập trung vào độ chính xác và sức mạnh.', 30, 0, 'Incomplete', 2)",
                "('Tập Chuyền Bóng', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập chuyền bóng. Làm việc với bạn để chuyền ngắn và dài, tập trung vào độ chính xác và thời gian.', 30, 0, 'Incomplete', 2)",
                "('Tập Phòng Thủ', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập kỹ năng phòng thủ. Tập trung vào vị trí, xoạc bóng và chặn đường chuyền để ngăn chặn tấn công của đối phương.', 30, 0, 'Incomplete', 2)",
                "('Tập Di Chuyển Chân', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập di chuyển chân. Cải thiện sự nhanh nhẹn và tốc độ với các bài tập thang và thay đổi hướng nhanh.', 30, 0, 'Incomplete', 2)",
                "('Điều Kiện Thể Lực', 'AnhBaiTap/Footwork Drill.png', 'Cải thiện điều kiện thể lực tổng quát. Tập trung vào sức bền, sức mạnh và các bài tập linh hoạt được điều chỉnh cho bóng đá.', 45, 0, 'Incomplete', 2)",
                "('Tập Tăng Tốc', 'AnhBaiTap/Footwork Drill.png', 'Cải thiện tốc độ. Thực hiện các bài tập chạy nước rút và nhanh nhẹn để tăng tốc độ trên sân.', 30, 0, 'Incomplete', 2)",
                "('Tập Sức Mạnh', 'AnhBaiTap/Footwork Drill.png', 'Tăng cường cơ bắp sử dụng trong bóng đá. Bao gồm các bài tập như squats, lunges và các bài tập cho phần lõi cơ thể.', 45, 0, 'Incomplete', 2)",
                "('Tập Linh Hoạt', 'AnhBaiTap/Footwork Drill.png', 'Cải thiện sự linh hoạt. Kéo giãn các nhóm cơ chính để tăng phạm vi chuyển động và ngăn ngừa chấn thương.', 30, 0, 'Incomplete', 2)",
                "('Mô Phỏng Trận Đấu', 'AnhBaiTap/Footwork Drill.png', 'Mô phỏng một trận đấu thật. Luyện tập các tình huống giống như trận đấu để cải thiện ra quyết định và làm việc nhóm dưới áp lực.', 60, 0, 'Incomplete', 2)",
                "('Tập Đá Phạt Góc', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập đá phạt góc. Nhắm vào các mục tiêu cụ thể trong vòng cấm, tập trung vào độ chính xác và sức mạnh.', 30, 0, 'Incomplete', 2)",
                "('Tập Đá Phạt Trực Tiếp', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập đá phạt trực tiếp. Nhắm vào các mục tiêu khác nhau trong khung thành, tập trung vào độ chính xác và kỹ thuật.', 30, 0, 'Incomplete', 2)",
                "('Tập Đá Phạt Đền', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập đá phạt đền. Tập trung vào độ chính xác, sức mạnh và vị trí.', 30, 0, 'Incomplete', 2)",
                "('Tập Thủ Môn', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập kỹ năng thủ môn. Tập trung vào vị trí, ngăn chặn cú sút và phân phối bóng.', 30, 0, 'Incomplete', 2)",
                "('Thảo Luận Chiến Thuật Đội', 'AnhBaiTap/Footwork Drill.png', 'Thảo luận và luyện tập chiến thuật đội. Lên kế hoạch các pha bóng và vị trí để tối đa hiệu quả.', 45, 0, 'Incomplete', 2)",
                "('Tập Thời Gian Phản Xạ', 'AnhBaiTap/Footwork Drill.png', 'Cải thiện thời gian phản xạ. Thực hiện các bài tập yêu cầu phản ứng nhanh với các kích thích khác nhau.', 30, 0, 'Incomplete', 2)",
                "('Tập Sức Bền', 'AnhBaiTap/Footwork Drill.png', 'Xây dựng sức bền. Thực hiện các bài tập tim mạch để tăng sức bền cho các trận đấu dài.', 45, 0, 'Incomplete', 2)",
                "('Tập Đánh Đầu', 'AnhBaiTap/Footwork Drill.png', 'Luyện tập đánh đầu. Tập trung vào thời gian, độ chính xác và sức mạnh.', 30, 0, 'Incomplete', 2)",
                "('Tập Nhanh Nhẹn', 'AnhBaiTap/Footwork Drill.png', 'Cải thiện sự nhanh nhẹn. Thực hiện các bài tập thay đổi hướng nhanh và thang.', 30, 0, 'Incomplete', 2)",
                "('Hạ Nhiệt', 'AnhBaiTap/Footwork Drill.png', 'Hạ nhiệt và kéo giãn sau buổi tập. Thực hiện kéo giãn nhẹ nhàng để giúp phục hồi cơ bắp và tăng linh hoạt.', 20, 0, 'Incomplete', 2)",
                // Bóng chuyền
                "('Chạy Bộ Khởi Động', 'AnhBaiTap/Warm-up Run.png', 'Thực hiện chạy bộ khởi động. Bắt đầu ở tốc độ chậm để từ từ tăng nhịp tim và làm dịu cơ bắp.', 30, 0, 'Incomplete', 3)",
                "('Chạy Bộ Xa', 'AnhBaiTap/Warm-up Run.png', 'Chạy các khoảng cách dài. Giữ tốc độ ổn định, tập trung vào sức bền và kỹ thuật đúng.', 60, 0, 'Incomplete', 3)",
                "('Chạy Bộ Các Khoảng Cách Ngắn', 'AnhBaiTap/Warm-up Run.png', 'Thực hiện các khoảng cách chạy nhanh. Thay phiên nhau giữa chạy nhanh trong thời gian ngắn và thời gian phục hồi.', 45, 0, 'Incomplete', 3)",
                "('Lặp Lại Chạy Lên Đồi', 'AnhBaiTap/Warm-up Run.png', 'Chạy lặp lại lên đồi. Tập trung vào sức mạnh và sức mạnh trong lúc leo lên, và phục hồi trong lúc đi xuống.', 45, 0, 'Incomplete', 3)",
                "('Chạy Bộ Nhịp Độ', 'AnhBaiTap/Warm-up Run.png', 'Chạy với tốc độ khá gắng. Giữ một tốc độ thử thách để cải thiện ngưỡng lactic.', 30, 0, 'Incomplete', 3)",
                "('Tập Luyện Fartlek', 'AnhBaiTap/Warm-up Run.png', 'Hòa nhập chơi tốc độ vào chạy bộ. Thay đổi giữa chạy nhanh và chậm dựa trên cảm nhận cá nhân.', 45, 0, 'Incomplete', 3)",
                "('Chạy Bộ Hồi Phục', 'AnhBaiTap/Warm-up Run.png', 'Thực hiện chạy bộ hồi phục. Chạy với tốc độ rất chậm để giúp cơ bắp phục hồi sau các buổi tập nặng.', 30, 0, 'Incomplete', 3)",
                "('Tập Sức Mạnh', 'AnhBaiTap/Warm-up Run.png', 'Tăng cường cơ bắp được sử dụng khi chạy bộ. Tập trung vào các bài tập như squats, lunges và các bài tập cho phần lõi cơ thể.', 45, 0, 'Incomplete', 3)",
                "('Tập Linh Hoạt', 'AnhBaiTap/Warm-up Run.png', 'Cải thiện linh hoạt. Kéo giãn các nhóm cơ chính để tăng phạm vi chuyển động và ngăn ngừa chấn thương.', 30, 0, 'Incomplete', 3)",
                "('Chạy Bộ Tăng Cường Sức Bền', 'AnhBaiTap/Warm-up Run.png', 'Chạy trong một khoảng thời gian dài. Tập trung vào xây dựng sức bền với tốc độ vừa phải, ổn định.', 60, 0, 'Incomplete', 3)",
                "('Luyện Tốc Độ', 'AnhBaiTap/Warm-up Run.png', 'Hòa nhập luyện tập tốc độ vào chế độ tập luyện của bạn. Thực hiện các khoảng thời gian hoặc lặp lại ở mức độ cao.', 45, 0, 'Incomplete', 3)",
                "('Chạy Bộ Tại Tốc Độ Đích', 'AnhBaiTap/Warm-up Run.png', 'Chạy ở tốc độ đích mục tiêu của bạn. Thực hành duy trì một tốc độ nhất định trên một khoảng cách cụ thể.', 30, 0, 'Incomplete', 3)",
                "('Chạy Bộ Hạ Nhiệt', 'AnhBaiTap/Warm-up Run.png', 'Hạ nhiệt bằng chạy bộ nhẹ nhàng. Dần dần giảm tốc độ để giảm nhịp tim.', 20, 0, 'Incomplete', 3)",
                "('Tập Luyện Đa Dạng', 'AnhBaiTap/Warm-up Run.png', 'Hòa nhập các hoạt động luyện tập đa dạng. Bao gồm đạp xe, bơi lội hoặc các bài tập nhẹ nhàng khác.', 45, 0, 'Incomplete', 3)",
                "('Tập Luyện Các Khoảng Thời Gian', 'AnhBaiTap/Warm-up Run.png', 'Thực hiện tập luyện các khoảng thời gian. Thay đổi giữa chạy với tốc độ cao và các giai đoạn phục hồi.', 45, 0, 'Incomplete', 3)",
                "('Chạy Bộ Tiến Triển', 'AnhBaiTap/Warm-up Run.png', 'Bắt đầu ở tốc độ chậm và từ từ tăng tốc độ. Kết thúc chạy với tốc độ nhanh hơn.', 45, 0, 'Incomplete', 3)",
                "('Chạy Bộ Trên Đường Mòn', 'AnhBaiTap/Warm-up Run.png', 'Chạy bộ trên đường mòn. Tận hưởng đa dạng địa hình và tập trung vào sự cân bằng và sức mạnh.', 60, 0, 'Incomplete', 3)",
                "('Chạy Bộ Nhóm', 'AnhBaiTap/Warm-up Run.png', 'Chạy cùng nhóm. Tận hưởng mối quan hệ xã hội và sự động viên từ việc chạy cùng người khác.', 60, 0, 'Incomplete', 3)",
                "('Tập Luyện Trên Đường Chạy', 'AnhBaiTap/Warm-up Run.png', 'Thực hiện tập luyện trên đường chạy. Thực hiện các khoảng thời gian hoặc lặp lại trên đường chạy để tập trung vào tốc độ và kỹ thuật.', 45, 0, 'Incomplete', 3)",
                "('Hạ Nhiệt', 'AnhBaiTap/Warm-up Run.png', 'Hạ nhiệt và kéo giãn sau khi chạy. Thực hiện kéo giãn nhẹ nhàng để giúp phục hồi cơ bắp và linh hoạt.', 20, 0, 'Incomplete', 3)",
                // Tenis
                "('Tập Luyện Giao Bóng', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện giao bóng. Tập trung vào độ chính xác và sự nhất quán, nhắm vào các vùng khác nhau của sân đối thủ.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Đánh Phá Lưới', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện đánh phá lưới. Luyện tập thời điểm và sức mạnh để đánh cầu xuống mạnh vào sân đối thủ.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Động Tác Chân', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện động tác chân. Di chuyển hiệu quả xung quanh sân, duy trì sự cân bằng và kiểm soát.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Đánh Drop Shot', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện đánh drop shot. Nhắm vào đánh cầu chỉ qua lưới để làm khó đối thủ phản công.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Chơi Gần Lưới', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện chơi gần lưới. Tập trung vào phản xạ nhanh và kiểm soát chính xác để chiếm ưu thế trong khu vực gần lưới.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Phòng Thủ', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện kỹ năng phòng thủ. Tập trung vào vị trí, thời điểm và phản ứng nhanh để phản công lại cú đánh và cú đẩy.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Đánh Clear', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện đánh clear. Đánh cầu cao và sâu vào sân đối thủ để có thời gian phục hồi.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Sức Bền', 'AnhBaiTap/Drop Shot Drill.png', 'Cải thiện sức bền tổng thể. Tập trung vào bài tập bền, sức mạnh và linh hoạt phù hợp với tennis.', 45, 0, 'Incomplete', 4)",
                "('Tập Luyện Tốc Độ', 'AnhBaiTap/Drop Shot Drill.png', 'Cải thiện tốc độ. Thực hiện các pha chạy nhanh và các bài tập linh hoạt để cải thiện thời gian phản ứng trên sân.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Sức Mạnh', 'AnhBaiTap/Drop Shot Drill.png', 'Tăng cường các cơ bắp được sử dụng trong tennis. Bao gồm các bài tập như squats, lunges và tập luyện cho phần cơ thể trên.', 45, 0, 'Incomplete', 4)",
                "('Tập Luyện Linh Hoạt', 'AnhBaiTap/Drop Shot Drill.png', 'Cải thiện linh hoạt. Kéo giãn các nhóm cơ chính để tăng khả năng di chuyển và ngăn ngừa chấn thương.', 30, 0, 'Incomplete', 4)",
                "('Mô Phỏng Trận Đấu', 'AnhBaiTap/Drop Shot Drill.png', 'Mô phỏng một trận đấu thực tế. Luyện tập các tình huống giống như trong trò chơi để cải thiện khả năng ra quyết định và làm việc nhóm dưới áp lực.', 60, 0, 'Incomplete', 4)",
                "('Phiên Chiến Lược', 'AnhBaiTap/Drop Shot Drill.png', 'Thảo luận và thực hành chiến lược. Lập kế hoạch các cú đánh và vị trí để tối đa hóa hiệu quả.', 45, 0, 'Incomplete', 4)",
                "('Tập Luyện Thời Gian Phản Ứng', 'AnhBaiTap/Drop Shot Drill.png', 'Cải thiện thời gian phản ứng. Thực hiện các bài tập yêu cầu phản ứng nhanh với các tác nhân khác nhau.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Sức Bền', 'AnhBaiTap/Drop Shot Drill.png', 'Xây dựng sức bền. Thực hiện các bài tập thể dục tim mạch để tăng sức bền cho các trận đấu dài.', 45, 0, 'Incomplete', 4)",
                "('Tập Luyện Nhanh Nhẹn', 'AnhBaiTap/Drop Shot Drill.png', 'Cải thiện sự nhanh nhẹn. Thực hiện các thay đổi hướng nhanh chóng và các bài tập leo thang.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Cùng Đối Tác', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện cùng đối tác. Luyện tập sự phối hợp và đồng bộ thời gian với các bài tập liên quan đến hai người chơi.', 30, 0, 'Incomplete', 4)",
                "('Tập Luyện Trả Giao Bóng', 'AnhBaiTap/Drop Shot Drill.png', 'Tập luyện trả giao bóng. Tập trung vào độ chính xác và kiểm soát để có lợi thế sau khi đối thủ giao bóng.', 30, 0, 'Incomplete', 4)",
                "('Hạ Nhiệt', 'AnhBaiTap/Drop Shot Drill.png', 'Hạ nhiệt và kéo giãn sau khi tập luyện. Thực hiện kéo giãn nhẹ nhàng để giúp phục hồi cơ bắp và linh hoạt.', 20, 0, 'Incomplete', 4)",
                "('Tập Luyện Tinh Thần', 'AnhBaiTap/Drop Shot Drill.png', 'Luyện tập sự bền bỉ tinh thần. Thực hành kỹ thuật tập trung và chiến lược để duy trì bình tĩnh dưới áp lực.', 30, 0, 'Incomplete', 4)",
                // Aerobic
                "('Gập bụng', 'AnhBaiTap/Push-ups.png', 'Thực hiện động tác gập bụng. Giữ cơ thể thẳng, hạ ngực xuống đất và đẩy lên lại.', 30, 0, 'Incomplete', 5)",
                "('Nâng tạ', 'AnhBaiTap/Push-ups.png', 'Tập nâng tạ. Tập trung vào các nhóm cơ chính như tạ đạp ngực, nâng tạ chết và chân gậy.', 45, 0, 'Incomplete', 5)",
                "('Kéo thẳng', 'AnhBaiTap/Push-ups.png', 'Thực hiện kéo thẳng. Treo trên thanh, kéo cằm lên trên thanh và hạ xuống.', 30, 0, 'Incomplete', 5)",
                "('Cuộn cánh tay', 'AnhBaiTap/Push-ups.png', 'Thực hiện cuộn cánh tay. Sử dụng tạ hoặc thanh tạ để nâng tạp trung tâm cánh tay.', 30, 0, 'Incomplete', 5)",
                "('Dáng hạ cơ thể', 'AnhBaiTap/Push-ups.png', 'Thực hiện động tác dáng hạ cơ thể. Hạ cơ thể bằng cách cong khuỷu tay và đẩy lên trở lại.', 30, 0, 'Incomplete', 5)",
                "('Đạp chân', 'AnhBaiTap/Push-ups.png', 'Sử dụng máy đạp chân. Tập trung đẩy tạ với chân, giữ lưng được hỗ trợ.', 45, 0, 'Incomplete', 5)",
                "('Đạp bàn', 'AnhBaiTap/Push-ups.png', 'Thực hiện đạp bàn. Nằm trên bàn, hạ thanh tạ xuống ngực và đẩy lên lại.', 45, 0, 'Incomplete', 5)",
                "('Đạp chết', 'AnhBaiTap/Push-ups.png', 'Thực hiện đạp chết. Nâng thanh tạ từ dưới đất, giữ lưng thẳng và sử dụng chân.', 45, 0, 'Incomplete', 5)",
                "('Đạp kẹp', 'AnhBaiTap/Push-ups.png', 'Thực hiện đạp kẹp. Hạ cơ thể bằng cách uốn cong khuỷu tay và đẩy lên trở lại.', 45, 0, 'Incomplete', 5)",
                "('Đấm bóp', 'AnhBaiTap/Push-ups.png', 'Thực hiện đấm bóp. Nâng trọng lượng lên phía trên đầu, tập trung vào cơ vai.', 30, 0, 'Incomplete', 5)",
                "('Kéo lùn', 'AnhBaiTap/Push-ups.png', 'Thực hiện kéo lùn. Sử dụng máy kéo cánh tay để kéo thanh xuống ngực, tập trung vào cơ lưng.', 30, 0, 'Incomplete', 5)",
                "('Giữ dáng', 'AnhBaiTap/Push-ups.png', 'Giữ dáng nghiêng. Giữ cơ thể thẳng, được hỗ trợ bằng cánh tay và ngón chân.', 30, 0, 'Incomplete', 5)",
                "('Gập bụng', 'AnhBaiTap/Push-ups.png', 'Thực hiện gập bụng. Nằm ngửa, nâng thân trên về phía đầu gối và hạ xuống.', 30, 0, 'Incomplete', 5)",
                "('Đẩy lên', 'AnhBaiTap/Push-ups.png', 'Thực hiện đẩy lên và đẩy lên. Giữ cơ thể thẳng, hỗ trợ bằng cánh tay và ngón chân.', 30, 0, 'Incomplete', 5)",
                "('Cửng chân', 'AnhBaiTap/Push-ups.png', 'Thực hiện cửng chân. Nâng gót chân lên khỏi mặt đất, tập trung vào cơ bắp cẳng.', 30, 0, 'Incomplete', 5)",
                "('Đạp bộ', 'AnhBaiTap/Push-ups.png', 'Thực hiện đạp bộ. Hạ cơ thể bằng cách cong khuỷu tay, giữ lưng thẳng và đứng trở lại.', 30, 0, 'Incomplete', 5)",
                "('Nghỉ ngơi', 'AnhBaiTap/Push-ups.png', 'Nghỉ ngơi và kéo dãn sau khi nâng tạ. Thực hiện kéo dãn nhẹ để giúp cơ bắp phục hồi và linh hoạt.', 20, 0, 'Incomplete', 5)",
                "('Cuộn cuộn', 'AnhBaiTap/Push-ups.png', 'Sử dụng cuộn cuộn. Cuộn qua các nhóm cơ chính để giải phóng căng thẳng và hỗ trợ phục hồi.', 30, 0, 'Incomplete', 5)",
                // Giảm mỡ
                "('Cardio', 'AnhBaiTap/Cardio.png', 'Thực hiện các bài tập cardio. Tham gia vào các hoạt động như chạy, đạp xe, hoặc bơi lội để tăng nhịp tim và đốt cháy calo.', 45, 0, 'Incomplete', 6)",
                "('HIIT', 'AnhBaiTap/HIIT.png', 'Thực hiện các bài tập cường độ cao ngắt quãng. Xen kẽ giữa các đợt tập luyện cường độ cao ngắn và các khoảng thời gian phục hồi.', 30, 0, 'Incomplete', 6)",
                "('Cycling', 'AnhBaiTap/Cycling.png', 'Đi xe đạp. Đạp xe ngoài trời hoặc sử dụng xe đạp tập để cải thiện sức khỏe tim mạch và đốt cháy chất béo.', 60, 0, 'Incomplete', 6)",
                "('Jump Rope', 'AnhBaiTap/Jump Rope.png', 'Nhảy dây. Thực hiện các bước nhảy liên tục, tập trung vào việc duy trì nhịp điệu đều đặn và tăng cường độ.', 30, 0, 'Incomplete', 6)",
                "('Running', 'AnhBaiTap/Running.png', 'Chạy bộ. Duy trì một tốc độ ổn định, nhắm đến việc tăng quãng đường và tốc độ theo thời gian.', 45, 0, 'Incomplete', 6)",
                "('Swimming', 'AnhBaiTap/Swimming.png', 'Bơi lội. Tham gia vào các kiểu bơi khác nhau để làm việc các nhóm cơ khác nhau và cải thiện sức khỏe tim mạch.', 45, 0, 'Incomplete', 6)",
                "('Rowing', 'AnhBaiTap/Rowing.png', 'Sử dụng máy chèo thuyền. Thực hiện các động tác chèo liên tục, tập trung vào việc tham gia toàn thân và tăng cường độ.', 45, 0, 'Incomplete', 6)",
                "('Stair Climbing', 'AnhBaiTap/Stair Climbing.png', 'Leo cầu thang. Sử dụng máy leo cầu thang hoặc leo cầu thang thực tế để tăng nhịp tim và làm việc các cơ phần dưới cơ thể.', 30, 0, 'Incomplete', 6)",
                // Tăng cơ
                "('Bench Press', 'AnhBaiTap/Bench Press.png', 'Thực hiện yoga. Thực hiện theo một bài tập yoga để cải thiện sự linh hoạt, cân bằng và thư giãn tinh thần.', 45, 0, 'Incomplete', 7)",
                "('Dumbbell Pullover', 'AnhBaiTap/Dumbbell Pullover.png', 'Thực hiện các bài tập kéo căng. Tập trung vào các nhóm cơ chính để cải thiện phạm vi chuyển động và ngăn ngừa chấn thương.', 30, 0, 'Incomplete', 7)",
                "('Bent Over Barbell Row', 'AnhBaiTap/Bent Over Barbell Row.png', 'Chạy bộ nhẹ nhàng. Duy trì tốc độ chậm, ổn định để giữ cho cơ thể hoạt động mà không quá sức.', 30, 0, 'Incomplete', 7)",
                "('Pull Up', 'AnhBaiTap/Pull Up.png', 'Thực hiện Pilates. Theo dõi một bài tập để cải thiện sức mạnh cơ, sự linh hoạt và kiểm soát toàn thân.', 45, 0, 'Incomplete', 7)",
                "('Squat ', 'AnhBaiTap/Squat.png', 'Đi bộ. Duy trì một tốc độ ổn định để giữ cho cơ thể hoạt động và cải thiện sức khỏe tim mạch.', 30, 0, 'Incomplete', 7)",
                "('Leg Press', 'AnhBaiTap/Leg Press.png', 'Luyện tập Thái Cực Quyền. Thực hiện một loạt các động tác chậm, kiểm soát để cải thiện sự cân bằng và thư giãn tinh thần.', 45, 0, 'Incomplete', 7)",
                "('Seated Dumbbell Shoulder Press', 'AnhBaiTap/Seated Dumbbell Shoulder Press.png', 'Thực hành thiền. Tập trung vào kỹ thuật thở và thư giãn để cải thiện sự rõ ràng tinh thần và giảm căng thẳng.', 30, 0, 'Incomplete', 7)",
                "('Upright Row','AnhBaiTap/Upright Row.png', 'Thực hiện các bài tập cân bằng. Tập trung vào sự ổn định và phối hợp để duy trì sức khỏe thể chất.', 30, 0, 'Incomplete', 7)",
                "('Dip', 'AnhBaiTap/Dip.png', 'Thực hiện các bài tập cơ. Thực hiện các động tác như plank và crunches để tăng cường cơ bắp cốt lõi.', 30, 0, 'Incomplete', 7)",
                // Duy trì
                "('Push-ups', 'AnhBaiTap/PushUps.png', 'Thực hiện chống đẩy để tăng cường cơ ngực, vai và cơ tam đầu. Giữ một đường thẳng từ đầu đến gót chân.', 30, 0, 'Incomplete', 8)",
                "('Squats', 'AnhBaiTap/Squats.png', 'Thực hiện squat để làm việc các cơ phần dưới cơ thể, bao gồm cơ đùi, cơ hamstrings và cơ mông. Giữ lưng thẳng.', 40, 0, 'Incomplete', 8)",
                "('Plank', 'AnhBaiTap/Plank.png', 'Giữ tư thế plank để tăng cường cơ cốt lõi. Đảm bảo cơ thể của bạn tạo thành một đường thẳng từ đầu đến gót chân.', 60, 0, 'Incomplete', 8)",
                "('Lunges', 'AnhBaiTap/Lunges.png', 'Thực hiện các động tác lunge để làm việc các cơ đùi và cơ mông. Bước về phía trước với một chân và hạ hông cho đến khi cả hai đầu gối cong.', 35, 0, 'Incomplete', 8)",
                "('Burpees', 'AnhBaiTap/Burpees.png', 'Thực hiện burpees cho một bài tập toàn thân tăng cường sức mạnh và thể lực tim mạch. Nhảy, squat và chống đẩy trong một động tác.', 50, 0, 'Incomplete', 8)",
                "('Mountain Climbers', 'AnhBaiTap/MountainClimbers.png', 'Thực hiện mountain climbers để tham gia cơ cốt lõi và cải thiện thể lực tim mạch. Đưa gối lên ngực luân phiên.', 45, 0, 'Incomplete', 8)",
                "('Bicycle Crunches', 'AnhBaiTap/BicycleCrunches.png', 'Thực hiện bicycle crunches để làm việc cơ bụng và cơ chéo. Chạm khuỷu tay vào đầu gối đối diện trong khi duỗi chân kia.', 30, 0, 'Incomplete', 8)",
                "('Leg Raises', 'AnhBaiTap/LegRaises.png', 'Thực hiện leg raises để tăng cường cơ bụng dưới. Nâng chân lên khỏi mặt đất trong khi giữ chúng thẳng.', 40, 0, 'Incomplete', 8)",
                "('Jumping Jacks', 'AnhBaiTap/JumpingJacks.png', 'Thực hiện jumping jacks để tăng nhịp tim và làm việc toàn thân. Nhảy trong khi mở rộng cánh tay và chân.', 20, 0, 'Incomplete', 8)",
                "('Side Plank', 'AnhBaiTap/SidePlank.png', 'Giữ tư thế side plank để làm việc cơ chéo và cải thiện sự ổn định cốt lõi. Giữ cơ thể trong một đường thẳng.', 50, 0, 'Incomplete', 8)",
                // Fast warmup
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
                "JOIN MONHOC ON BAITAP.MAMH = MONHOC.MAMH " +
                "WHERE BAITAP.MAMH = ? AND BAITAP.TRANGTHAI = 'Incomplete'";
        return db.rawQuery(query, new String[]{String.valueOf(MaMH)});
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
