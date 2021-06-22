package com.example.hpl_one.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hpl_one.Config;
import com.example.hpl_one.LoginActivity;
import com.example.hpl_one.R;

public class StudentActivity extends AppCompatActivity {
    private AppCompatButton easy, medium, difficult, quick_test;
    private ImageView logout;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVar();
        handlerEvent();
    }

    private void initVar() {
        pref        = getSharedPreferences(Config.LOGIN_STATE, MODE_PRIVATE);
        easy        = findViewById(R.id.easy);
        medium      = findViewById(R.id.medium);
        difficult   = findViewById(R.id.difficult);
        quick_test  = findViewById(R.id.quick_test);
        logout      = findViewById(R.id.logout);

        //(CURRENTLY) Disable quick test function
        quick_test.setEnabled(false);
    }

    private void handlerEvent() {
        Intent ques_intent = new Intent(StudentActivity.this, PrepareActivity.class);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques_intent.putExtra("level", "1");
                startActivity(ques_intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques_intent.putExtra("level", "2");
                startActivity(ques_intent);
            }
        });

        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques_intent.putExtra("level", "3");
                startActivity(ques_intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.remove(Config.LOGINED);
                boolean res = editor.commit();

                if (res) {
                    startActivity(new Intent(StudentActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Have errror when logout. Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}