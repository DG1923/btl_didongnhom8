package com.example.baitaplonnhom8;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonnhom8.database.DatabaseHelper;
import com.example.baitaplonnhom8.database.Models.User;

import java.util.ArrayList;
import java.util.List;
 public class huanluyencanhan extends AppCompatActivity {
        ListView listView;
        private TextView txtBMI,txtTheTrang,txtNameUser;
        private Button btn_duytri,btn_tangco,btn_giammo;
        private RecyclerView recyclerView,exerciseRecyclerView;
        private ExerciseAdapter exerciseAdapter;
        private vecticalExerciseAdapter vecticalAdapter;
        private List<Exercise> exerciseList;
        private DatabaseHelper databaseHelper;
        private Cursor cursor;

        private Button btn_logout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_huanluyencanhan);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            getWidget();
            databaseHelper = new DatabaseHelper(this);
            //Lấy thông tin user từ phiên đăng nhập
            User user = phienDangNhapUser.getUserFromPreferences(this);
            txtBMI.setText("BMI :"+String.format("%.2f",user.getBMI()));
            txtTheTrang.setText("Thể trạng : "+user.getCondition());

            // Thêm các bài tập khác vào danh sách
            //Thêm dữ liệu các bài tập mục Fast Warmup
            cursor = databaseHelper.getBaiTapByMaMH(9);
            exerciseAdapter = new ExerciseAdapter(this, cursor);
            recyclerView.setAdapter(exerciseAdapter);
            //Thêm dữ liệu mặc định cho chế độ tập luyện
            cursor = databaseHelper.getBaiTapByMaMH(6);
            vecticalAdapter = new vecticalExerciseAdapter(this,cursor);
            exerciseRecyclerView.setAdapter(vecticalAdapter);

            // Xử lý sự kiện cho các nút bấm chọn chế độ tập luyện
            btn_giammo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor = databaseHelper.getBaiTapByMaMH(6);
                    vecticalAdapter.setCursor(cursor);
                    vecticalAdapter.setContext(huanluyencanhan.this);
                    vecticalAdapter.notifyDataSetChanged();

                }
            });
            btn_tangco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor = databaseHelper.getBaiTapByMaMH(7);
                    vecticalAdapter.setCursor(cursor);
                    vecticalAdapter.notifyDataSetChanged();
                }
            });
            btn_duytri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor = databaseHelper.getBaiTapByMaMH(8);
                    vecticalAdapter.setCursor(cursor);
                    vecticalAdapter.notifyDataSetChanged();
                }
            });

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(huanluyencanhan.this,login.class);
                    startActivity(intent);
                    finish();
                }
            });

            // Xử lý sự kiện cho nút bấm chọn chế độ tập luyện
        }
        public void getWidget(){
            txtBMI = findViewById(R.id.txtBMI);
            txtTheTrang = findViewById(R.id.txtTheTrang);
            btn_duytri = findViewById(R.id.btn_duytri);
            btn_giammo = findViewById(R.id.btn_giammo);
            btn_tangco = findViewById(R.id.btn_tangco);
            btn_logout = findViewById(R.id.btn_logout);
    //        listView = findViewById(R.id.fast_warmup);
            txtNameUser = findViewById(R.id.txtNameUser);
            //        listView = findViewById(R.id.fast_warmup);
            recyclerView = findViewById(R.id.fastWarmup);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            exerciseRecyclerView = findViewById(R.id.exercies);
            exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        }
    }
