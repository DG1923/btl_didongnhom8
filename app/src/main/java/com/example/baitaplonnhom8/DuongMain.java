package com.example.baitaplonnhom8;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DuongMain extends AppCompatActivity {
    TextView timer;
    ImageButton play;
    CountDownTimer countDownTimer;
    long timeLeftInMiliseconds = 100000;
    boolean timeRunning;
    ListView lvBaiTap;
    ArrayList<BaiTap> arrayBaiTap;
    BaiTapAdapter adapter;
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
        play = findViewById(R.id.playButton);
        timer = findViewById(R.id.timerLeft);
        lvBaiTap = findViewById(R.id.listEx);
        arrayBaiTap = new ArrayList<>();
        arrayBaiTap.add(new BaiTap("Khoi dong", "asdlkfjlk", R.drawable.exercise, 120, "Chua hoan thanh"));
        arrayBaiTap.add(new BaiTap("Khoi dong", "asdlkfjlk", R.drawable.exercise, 120, "Chua hoan thanh"));
        arrayBaiTap.add(new BaiTap("Khoi dong", "asdlkfjlk", R.drawable.exercise, 120, "Chua hoan thanh"));
        arrayBaiTap.add(new BaiTap("Khoi dong", "asdlkfjlk", R.drawable.exercise, 120, "Chua hoan thanh"));


        adapter = new BaiTapAdapter(this, R.layout.dong_bai_tap, arrayBaiTap);
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

    public void startStop(){
        if(timeRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMiliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMiliseconds = l;
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
        int minutes = (int) timeLeftInMiliseconds / 60000;
        int seconds = (int) timeLeftInMiliseconds % 60000 / 1000;
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