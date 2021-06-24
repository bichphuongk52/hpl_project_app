package com.example.hpl_one.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hpl_one.R;

public class StudentActivity extends AppCompatActivity {
    private AppCompatButton easy, medium, difficult, quick_test;
    private TextView student_preview_name;

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
        easy                    = findViewById(R.id.easy);
        medium                  = findViewById(R.id.medium);
        difficult               = findViewById(R.id.difficult);
        quick_test              = findViewById(R.id.quick_test);
        student_preview_name    = findViewById(R.id.student_preview_name);

        //(CURRENTLY) Disable quick test function
        quick_test.setEnabled(false);
        Intent x = getIntent();
        student_preview_name.setText(String.valueOf("Hi, "+x.getStringExtra("username")+"!"));
    }

    private void handlerEvent() {
        Intent ques_intent = new Intent(StudentActivity.this, PrepareActivity.class);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques_intent.putExtra("level", "0");
                startActivity(ques_intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques_intent.putExtra("level", "1");
                startActivity(ques_intent);
            }
        });

        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques_intent.putExtra("level", "2");
                startActivity(ques_intent);
            }
        });
    }
}