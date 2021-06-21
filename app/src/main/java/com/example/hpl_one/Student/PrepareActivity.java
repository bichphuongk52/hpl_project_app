package com.example.hpl_one.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hpl_one.R;

public class PrepareActivity extends AppCompatActivity {
    private AppCompatButton ready;
    private TextView desc;
    private int level;

    private String easy_mode = "Easy: let's practice basic grammar and vocabulary!";
    private String medium_mode = "Medium: Easy advanced mode. Right?";
    private String difficult_mode = "Difficult: You will become a master!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVar();
        setDesc();
        handlerEvent();
    }

    private void initVar() {
        ready       = findViewById(R.id.ready);
        desc        = findViewById(R.id.pre_desc);
    }

    private void setDesc() {
        Intent level_intent = getIntent();
        level = Integer.valueOf(level_intent.getStringExtra("level"));

        switch (level) {
            case 1:
                desc.setText(easy_mode);
                break;
            case 2:
                desc.setText(medium_mode);
                break;
            case 3:
                desc.setText(difficult_mode);
                break;
            default:
                desc.setText("Are you ready?");
        }
    }

    private void handlerEvent() {
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ques_intent = new Intent(PrepareActivity.this, QuesActivity.class);
                ques_intent.putExtra("level", String.valueOf(level));
                startActivity(ques_intent);
                finish();
            }
        });
    }
}