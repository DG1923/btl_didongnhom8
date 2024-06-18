package com.example.baitaplonnhom8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.report){
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.profile){
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
