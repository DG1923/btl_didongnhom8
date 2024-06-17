package com.example.baitaplonnhom8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonnhom8.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    Button btn_luyentapcanhan;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
        btn_luyentapcanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, huanluyencanhan.class);
                startActivity(intent);
            }
        });

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Get cursor for subjects
        Cursor cursor = getAllSubjects();

        // Initialize and set up the RecyclerView
        subjectAdapter = new SubjectAdapter(this, cursor, databaseHelper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(subjectAdapter);
    }

    private void getWidget() {
        btn_luyentapcanhan = findViewById(R.id.btn_trynow);
        recyclerView = findViewById(R.id.listview);
    }

    private Cursor getAllSubjects() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DatabaseHelper.DB_MONHOC, null);
    }
}
