package com.example.hpl_one.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hpl_one.Config;
import com.example.hpl_one.LoginActivity;
import com.example.hpl_one.Modules.Question;
import com.example.hpl_one.R;
import com.example.hpl_one.Services.APIConfig;
import com.example.hpl_one.Services.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {
    private AppCompatButton easy, medium, difficult, quick_test;
    private TextView student_preview_name;
    private SharedPreferences pref;
    private ImageView logout;
    private APIConfig f;
    private String email, ssid;

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
        logout                  = findViewById(R.id.logout);
        pref                    = getSharedPreferences(Config.LOGIN_STATE, MODE_PRIVATE);
        f                       = RetrofitConfig.JSONconfig().create(APIConfig.class);

        email                   =  pref.getString(Config.EMAIL, null);
        ssid                    =  pref.getString(Config.SSID, null);
        String username         =  pref.getString(Config.USER, null);

        Log.i("USERNAME", pref.getString(Config.USER, null));
        student_preview_name.setText(String.valueOf("Hi, "+username+"!"));

        //(CURRENTLY) Disable quick test function
        quick_test.setEnabled(false);

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Wait a minute!", Toast.LENGTH_SHORT).show();
                Call g = f.logout(ssid, email);
                g.enqueue(new Callback<Question>() {
                    @Override
                    public void onResponse(Call<Question> call, Response<Question> response) {
                        Toast.makeText(getApplicationContext(), "Logout!", Toast.LENGTH_SHORT).show();
                        pref.edit().clear().apply();
                        startActivity(new Intent(StudentActivity.this, LoginActivity.class));
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Question> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Unknow error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}