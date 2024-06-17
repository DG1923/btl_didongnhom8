package com.example.baitaplonnhom8;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DuongMain extends AppCompatActivity {
    TextView timer;
    TextView tenBaiTap;
    TextView huongDan;
    TextView monHoc;
    ImageView image;
    ImageButton play;
    CountDownTimer countDownTimer;
    long timeLeftSeconds = 100;  // Set initial time in seconds
    boolean timeRunning;
    RecyclerView lvBaiTap;
    ArrayList<BaiTap> arrayBaiTap;
    BaiTapAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    BaiTap currentBaiTap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_duong_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
        hienThiDatabase();
        play.setOnClickListener(v -> startStop());
        updateTimer();

        adapter.setOnItemClickListener(position -> {
            if (cursor.moveToPosition(position)) {
                String nameEx = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TENBT));
                String anhminhhoa = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_ANHMINHHOA));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_HUONGDAN));
                int timeRequired = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANYC));
                int timeReal = cursor.getInt((cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANTHUCTE)));
                // Update UI elements in DuongMain activity
                tenBaiTap.setText(nameEx);
                huongDan.setText(description);
                int timeleft = timeRequired - timeReal;
                timer.setText(String.valueOf(timeleft));
                try {
                    InputStream inputStream = getApplicationContext().getAssets().open(anhminhhoa);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    image.setImageBitmap(bitmap);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                timeLeftSeconds = timeRequired;
                updateTimer();

                if (timeRunning) {
                    stopTimer();
                }
            }
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
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.report) {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void hienThiDatabase() {
        databaseHelper = new DatabaseHelper(this);
        int getIdBT = getIntent().getIntExtra("idBT", -1);
        int getMahh = getIntent().getIntExtra("maMH", -1);
        tenBaiTap.setText("Ma bai tap " + getIdBT);
        monHoc.setText("Ma ma mon hoc " + getMahh);
        Exercise currentExercise = databaseHelper.getBaiTapByMaBT(getIdBT);
        tenBaiTap.setText(currentExercise.getName());
        huongDan.setText(currentExercise.getDecription());
        try {
            InputStream inputStream = getApplicationContext().getAssets().open(currentExercise.getImageResource());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            this.image.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cursor = databaseHelper.getBaiTapByMaMH(getMahh);
        adapter = new BaiTapAdapter(this, cursor);
        lvBaiTap.setAdapter(adapter);
        play.setBackgroundResource(R.drawable.playbutton);
    }

    private void getWidget() {
        monHoc = findViewById(R.id.textViewMon);
        tenBaiTap = findViewById(R.id.textViewTenBaiTap);
        huongDan = findViewById(R.id.textViewHuongDan);
        image = findViewById(R.id.imageViewHinhAnh);
        play = findViewById(R.id.playButton);
        timer = findViewById(R.id.timerLeft);
        lvBaiTap = findViewById(R.id.listEx);
        lvBaiTap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void startStop() {
        if (timeRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftSeconds * 1000, 1000) { // Convert seconds to milliseconds
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftSeconds = millisUntilFinished / 1000; // Convert milliseconds to seconds
                updateTimer();
            }

            @Override
            public void onFinish() {
                timeRunning = false;
                play.setBackgroundResource(R.drawable.playbutton);
            }
        }.start();

        play.setBackgroundResource(R.drawable.pausebutton);
        timeRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        play.setBackgroundResource(R.drawable.playbutton);
        timeRunning = false ;
        databaseHelper.updateActualTime(currentBaiTap.getMaBaiTap(), (int) (currentBaiTap.getThoiGianThucTe() + (currentBaiTap.getThoiGianYeuCau() - timeLeftSeconds)));
    }

    public void updateTimer() {
        int minutes = (int) timeLeftSeconds / 60;
        int seconds = (int) timeLeftSeconds % 60;
        String timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        timer.setText(timeLeftText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
