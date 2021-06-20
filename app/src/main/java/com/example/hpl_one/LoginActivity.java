package com.example.hpl_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText login_username, login_password, tv_signup;
    private CheckBox login_remember;
    private AppCompatButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVariable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hanldingEvent();
    }

    @SuppressLint("WrongViewCast")
    private void initVariable() {
        login_username  = findViewById(R.id.login_username);
        login_password  = findViewById(R.id.login_password);
        tv_signup       = findViewById(R.id.tv_signup);
        login_remember  = findViewById(R.id.login_remember);
        login_btn       = findViewById(R.id.login_btn);
        login_remember.setChecked(false);
    }

    private void hanldingEvent() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_username.getText().toString();
                String password = login_password.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username and password must be filled!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (true) {
                    //login condition here
                    if (true) {
                        //if student
                        Intent student_intent = new Intent(LoginActivity.this, StudentActivity.class);
                        student_intent.putExtra("username", username);
                        startActivity(student_intent);
                        finish();
                    }
//                    if (true) {
//                        //if admin
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        finish();
//                    }
                    if (login_remember.isChecked()) {
                        //Use SharePreferences here to save login state
                    } else {
                        //Detele save login state
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Username is not existed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}