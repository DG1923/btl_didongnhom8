package com.example.baitaplonnhom8;

import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.ArrayList;

public class DuongMain extends AppCompatActivity {
    TextView timer;
    TextView tenBaiTap;
    TextView huongDan;
    ImageView image;
    ImageButton play;
    CountDownTimer countDownTimer;
    long timeLeftSeconds = 100000;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
        databaseHelper = new DatabaseHelper(this);
        cursor = databaseHelper.getBaiTapByMaMH(7);

        adapter = new BaiTapAdapter(this,cursor);
        lvBaiTap.setAdapter(adapter);



        play.setBackgroundResource(R.drawable.playbutton);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        updateTimer();
        
    }

    private void getWidget() {
        tenBaiTap = findViewById(R.id.textViewTenBaiTap);
        huongDan = findViewById(R.id.textViewHuongDan);
        image = findViewById(R.id.imageViewHinhAnh);
        play = findViewById(R.id.playButton);
        timer = findViewById(R.id.timerLeft);
        lvBaiTap = findViewById(R.id.listEx);
        lvBaiTap.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    public void startStop(){
        if(timeRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }


    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftSeconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        play.setBackgroundResource(R.drawable.pausebutton);
        timeRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        play.setBackgroundResource(R.drawable.playbutton);
        timeRunning = false;
    }

    public void updateTimer(){
        int minutes = (int) timeLeftSeconds / 60;
        int seconds = (int) timeLeftSeconds % 60;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";

        if(seconds<10) timeLeftText += "0";
        timeLeftText += seconds;
        timer.setText(timeLeftText);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}