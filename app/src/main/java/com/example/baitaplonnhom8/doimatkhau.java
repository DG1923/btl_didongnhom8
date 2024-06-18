package com.example.baitaplonnhom8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baitaplonnhom8.database.DatabaseHelper;
import com.example.baitaplonnhom8.database.Models.User;

public class doimatkhau extends AppCompatActivity {
    EditText txt_confirmpassword,txt_newpassword,txt_oldpassword;
    Button btn_xacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doimatkhau);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWidget();
        User user = phienDangNhapUser.getUserFromPreferences(this);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_oldpassword.getText().toString()==user.getMATKHAU()){
                    if(txt_newpassword.getText().toString().equals(txt_confirmpassword.getText().toString())){
                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                        if(databaseHelper.changePassword(user.getMATK(),txt_newpassword.getText().toString())){
                            phienDangNhapUser.changePassWord(getApplicationContext(),txt_newpassword.getText().toString());
                            finish();
                        }else {
                            Toast.makeText(doimatkhau.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(doimatkhau.this, "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(doimatkhau.this, "Mật khẩu cũ không khớp", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void getWidget() {
        txt_confirmpassword = findViewById(R.id.txt_confirmpassword);
        txt_newpassword = findViewById(R.id.txt_newpassword);
        txt_oldpassword = findViewById(R.id.txt_oldpassword);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);
    }
}