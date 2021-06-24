package com.example.hpl_one.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hpl_one.Modules.Question;
import com.example.hpl_one.R;

public class QuesActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView ques;
    private AppCompatButton ans_a, ans_b, ans_c, ans_d;
    private long totalQues = 0;
    private int currentQuesId = 0;
    private Question x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVar();
    }

    private void initVar() {
        //init DB

        ques        = findViewById(R.id.student_ques);
        ans_a       = findViewById(R.id.ans_a);
        ans_b       = findViewById(R.id.ans_b);
        ans_c       = findViewById(R.id.ans_c);
        ans_d       = findViewById(R.id.ans_d);

        //Get amount of ques
        showQues();
    }

    private void showQues() {
        ques.setText(x.getQues());
        ans_a.setText("A. "+x.getAnswer_a());
        ans_b.setText("B. "+x.getAnswer_b());
        ans_c.setText("C. "+x.getAnswer_c());
        ans_d.setText("D. "+x.getAnswer_d());
        ans_a.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ans));
        ans_b.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ans));
        ans_c.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ans));
        ans_d.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ans));
    }

    @Override
    public void onClick(View v) {
        String choose;
        switch (v.getId()) {
            case R.id.ans_a:
                checkAns("A");
                break;
            case R.id.ans_b:
                checkAns("B");
                break;
            case R.id.ans_c:
                checkAns("C");
                break;
            case R.id.ans_d:
                checkAns("D");
                break;
            case R.id.ques_next:
                if ((currentQuesId < totalQues)) {
                    showQues();
                } else {
                    showQues();
                }
                break;
        }
    }

    private void checkAns(String ans) {
        switch (x.getCorrect_ans()) {
            case "A":
                ans_a.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct_ans));
                break;
            case "B":
                ans_b.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct_ans));
                break;
            case "C":
                ans_c.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct_ans));
                break;
            case "D":
                ans_d.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct_ans));
                break;
        }

        if (!ans.equalsIgnoreCase(x.getCorrect_ans())) {
            switch (ans) {
                case "A":
                    ans_a.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_ans));
                    break;
                case "B":
                    ans_b.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_ans));
                    break;
                case "C":
                    ans_c.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_ans));
                    break;
                case "D":
                    ans_d.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_ans));
                    break;
            }
        }
    }
}