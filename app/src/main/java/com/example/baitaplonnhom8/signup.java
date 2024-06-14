package com.example.baitaplonnhom8;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.baitaplonnhom8.database.repository.UserRepository;

public class signup extends AppCompatActivity {

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UserRepository
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        userRepository = new UserRepository(dbHelper);

        Button btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignup();
            }
        });
    }

    private void performSignup() {
        EditText txtName = findViewById(R.id.txt_name);
        EditText txtId = findViewById(R.id.txt_id);
        EditText txtHeight = findViewById(R.id.txt_chieucao);
        EditText txtWeight = findViewById(R.id.txt_cannang);
        EditText txtEmail = findViewById(R.id.txt_email);
        EditText txtPassword = findViewById(R.id.txt_password);
        EditText txtRePassword = findViewById(R.id.txt_repassword);

        String name = txtName.getText().toString().trim();
        String idStr = txtId.getText().toString().trim();
        int id = TextUtils.isEmpty(idStr) ? 0 : Integer.parseInt(idStr);
        String height = txtHeight.getText().toString().trim();
        String weight = txtWeight.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String rePassword = txtRePassword.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(idStr) || TextUtils.isEmpty(height) ||
                TextUtils.isEmpty(weight) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(rePassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        float heightValue, weightValue;

        try {
            heightValue = Float.parseFloat(height);
            weightValue = Float.parseFloat(weight);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid height or weight format", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement password hashing and salting before saving to database
        // For simplicity, we'll save as plaintext in this example (not recommended in production)

        // Create a User object and save to database
        User user = new User(id, name, heightValue, weightValue, email, password);
        // Attempt to sign up the user
        long signupSuccessful = userRepository.signUp(user);

        if (signupSuccessful!=-1) {
            // Signup successful
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            finish(); // Redirect to login screen after signup
        } else {
            // Signup failed
            Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show();
        }
    }
}
