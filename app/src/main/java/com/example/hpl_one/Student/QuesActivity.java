package com.example.hpl_one.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hpl_one.Modules.Question;
import com.example.hpl_one.R;
import com.example.hpl_one.SQLite.SQliteQuestionHelper;

import java.util.ArrayList;
import java.util.List;

public class QuesActivity extends AppCompatActivity {
    private TextView ques;
    private AppCompatButton ans_a, ans_b, ans_c, ans_d, next;
    private ImageView main_sc;
    private int currentQuesId = 0;
    private List<Question> data = new ArrayList<>();
    private Question x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        initQues();
        initVar();
        handlerEvent();
    }

    private void handlerEvent() {
        ans_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns("A");
            }
        });

        ans_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns("B");
            }
        });

        ans_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns("C");
            }
        });

        ans_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns("D");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentQuesId < data.size()-1)) {
                    showQues(++currentQuesId);
                } else {
                    showQues(0);
                }
            }
        });

        main_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuesActivity.this, StudentActivity.class));
                finish();
            }
        });
    }

    private void initQues() {
        if (data != null) data.clear();
        data.add(new Question(0, "In many western cultures, the White Lily is a flower that is representative of purity and sweetness\n What is the same meaning with 'representative'?", "advantageous", "marvelous", "symbolic", "universal", "C", "1"));
        data.add(new Question(1, "I can’t figure out Daisy’s attitude toward the trip – she is blowing hot and cold about it. First she’s all excited, but then she seems reluctant to go\n What is the same meaning with 'is blowing hot and cold'?", "keeps talking about trivial things", "keeps going", "keeps changing her mood", "keeps making you confused", "C", "1"));
        data.add(new Question(2, "Social networking aims at promoting and aiding communication. _____", "However", "Otherwise", "Moreover", "As a result", "A", "1"));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initVar() {
        ques        = findViewById(R.id.student_ques);
        ans_a       = findViewById(R.id.ans_a);
        ans_b       = findViewById(R.id.ans_b);
        ans_c       = findViewById(R.id.ans_c);
        ans_d       = findViewById(R.id.ans_d);
        next        = findViewById(R.id.ques_next);
        main_sc     = findViewById(R.id.main_sc);

        //Get amount of ques
        showQues(currentQuesId);
    }

    private void showQues(int id) {
        ans_a.setEnabled(true);
        ans_b.setEnabled(true);
        ans_c.setEnabled(true);
        ans_d.setEnabled(true);
        x = data.get(id);
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

    private void checkAns(String ans) {
        ans_a.setEnabled(false);
        ans_b.setEnabled(false);
        ans_c.setEnabled(false);
        ans_d.setEnabled(false);
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