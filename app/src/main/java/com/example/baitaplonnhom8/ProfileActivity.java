package com.example.baitaplonnhom8;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baitaplonnhom8.database.Models.User;

public class ProfileActivity extends AppCompatActivity {
    EditText txt_id,txt_name,txt_email,txt_chieucao,txt_cannang,txt_password;
    Button btn_submit,btn_changePasswords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        getWidget();
        //lấy thông tin user từ phiên đăng nhập
        User user = phienDangNhapUser.getUserFromPreferences(this);
        //Điền thông tin từ phiên đăng nhập vào các edittext
        txt_id.setText(user.getMATK()+"");
        txt_name.setText(user.getHOTEN()+"");
        txt_email.setText(user.getEMAIL()+"");
        txt_chieucao.setText(user.getCHIEUCAO()+"");
        txt_cannang.setText(user.getCANNANG()+"");
        txt_password.setText(user.getMATKHAU());

        btn_submit.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        btn_changePasswords.setOnClickListener(v -> {
            Intent intent = new Intent(this, doimatkhau.class);
            startActivity(intent);
        });

    }

    private void getWidget() {
        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_hoten);
        txt_email = findViewById(R.id.txt_email);
        txt_chieucao = findViewById(R.id.txt_chieucao);
        txt_cannang = findViewById(R.id.txt_cannang);
        txt_password = findViewById(R.id.txt_password);
        btn_submit = findViewById(R.id.btn_xacnhan);
        btn_changePasswords = findViewById(R.id.btn_doimatkhau);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.report){
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}