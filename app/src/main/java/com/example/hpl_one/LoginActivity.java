package com.example.hpl_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hpl_one.Student.StudentActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText login_username, login_password;
    private CheckBox login_remember;
    private AppCompatButton login_btn;
    private SharedPreferences pref;

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
        pref = getSharedPreferences(Config.LOGIN_STATE, MODE_PRIVATE);
        login_username  = findViewById(R.id.login_username);
        login_password  = findViewById(R.id.login_password);
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

                //login condition here
                if (true) {

                    //if student
                    if (true) {
                        Intent student_intent = new Intent(LoginActivity.this, StudentActivity.class);
                        student_intent.putExtra("username", username);
                        startActivity(student_intent);
                        finish();
                    }

                    //if admin
//                    if (true) {
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        finish();
//                    }
                    if (login_remember.isChecked()) {
                        //Use SharePreferences here to save login state
                        pref.edit().putString(Config.LOGINED, String.valueOf(true)).apply();
                    } else {
                        //Detele save login state
                        SharedPreferences.Editor editor = pref.edit();
                        editor.remove(Config.LOGINED);
                        editor.apply();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Account is not existed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}