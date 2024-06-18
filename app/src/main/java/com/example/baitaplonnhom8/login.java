package com.example.baitaplonnhom8;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplonnhom8.database.DatabaseHelper;
import com.example.baitaplonnhom8.database.Models.User;
import com.example.baitaplonnhom8.database.repository.UserRepository;

public class login extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnLogin, btnSignup;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        userRepository = new UserRepository(dbHelper);

        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean loggedIn;
        if (userRepository.login(email, password)) loggedIn = true;
        else loggedIn = false;
        if (loggedIn) {
            DatabaseHelper db = new DatabaseHelper(this);
            // Successful login, navigate to MainActivity
            User user = db.getUserByEmail(email);
            phienDangNhapUser.saveUserToPreferences(this,user);
            Intent intent = new Intent(login.this, MainActivity.class);

            startActivity(intent);
            finish(); // Prevents user from returning to login screen when pressing back
        } else {
            // Login failed
            Toast.makeText(login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
