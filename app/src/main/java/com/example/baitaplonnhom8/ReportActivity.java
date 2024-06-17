package com.example.baitaplonnhom8;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ReportActivity extends AppCompatActivity {
    private TextView tvTitle;
    private Button btn_done;
    private Button btn_notdone;


    private int subjectId; // Assuming this is the declaration

    // Setter and getter methods for subjectId
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = (subjectId != null) ? subjectId : 0; // Assigning default value if null
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report); // Set the XML layout file

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        tvTitle = findViewById(R.id.tv_title);
        btn_done = findViewById(R.id.btn_done);
        btn_notdone = findViewById(R.id.btn_notdone);

        // Check if subjectId is valid
        if (subjectId == 0) {
            // Handle the case where subjectId is not set
            tvTitle.setText("No Subject Selected");
            btn_done.setEnabled(false); // Disable buttons or handle them appropriately
            btn_notdone.setEnabled(false);
            return;
        }

        // Fetch subject name from database based on subjectId
        String subjectName = getSubjectNameFromDatabase(subjectId);

        // Set title dynamically
        if (subjectName != null) {
            setTitle("Report: " + subjectName);
        } else {
            setTitle("Report");
        }

        // Set listeners or implement functionality for buttons if needed
        btn_done.setOnClickListener(view -> {
            // Handle button click
        });

        btn_notdone.setOnClickListener(view -> {
            // Handle button click
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.profile){
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to fetch subject name from database
    private String getSubjectNameFromDatabase(int subjectId) {
        if (subjectId == 0) {
            return null; // Handle the case where subjectId is not set
        }
        // Replace with your actual implementation to fetch subject name from database
        // Use your DatabaseHelper or SQLiteOpenHelper instance to execute SQL query
        // and return subject name based on subjectId
        // Example:
        // return databaseHelper.getSubjectName(subjectId);
        return "Sample Subject"; // Placeholder return value
    }
}
