package com.example.hpl_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.hpl_one.Student.StudentActivity;

public class LoadingActivity extends AppCompatActivity {
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        pref = getSharedPreferences(Config.LOGIN_STATE, MODE_PRIVATE);
        new LoadingScreen().start();
    }

    private class LoadingScreen extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Check user login or not
            if (pref.contains(Config.SSID) && !pref.getString(Config.SSID, null).isEmpty()) {
                //if login state is saved in SharePreferences
                Intent student_intent = new Intent(LoadingActivity.this, StudentActivity.class);
                student_intent.putExtra("username", String.valueOf(pref.getString(Config.USER, null)));
                startActivity(student_intent);
            } else {
                //if user not login or state is not exist
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
            }
            finish();
        }
    }
}