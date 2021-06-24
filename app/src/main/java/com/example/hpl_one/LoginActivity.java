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

import com.example.hpl_one.Modules.User;
import com.example.hpl_one.Services.APIConfig;
import com.example.hpl_one.Services.RetrofitConfig;
import com.example.hpl_one.Student.QuesActivity;
import com.example.hpl_one.Student.StudentActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText login_email, login_password;
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
        login_email  = findViewById(R.id.login_email);
        login_password  = findViewById(R.id.login_password);
        login_remember  = findViewById(R.id.login_remember);
        login_btn       = findViewById(R.id.login_btn);
        login_remember.setChecked(false);
    }

    private void hanldingEvent() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = login_email.getText().toString();
                String password = login_password.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email and password must be filled!", Toast.LENGTH_SHORT).show();
                    return;
                }
                login_btn.setClickable(false);
                login_btn.setText("Wait...");
                APIConfig x = RetrofitConfig.JSONconfig().create(APIConfig.class);
                Call<User> g = x.login(email, password);
                g.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User u = response.body();

//                            if (login_remember.isChecked()) {
//                                //Use SharePreferences here to save SSID
//                                if (
//                                       pref.edit().putString(Config.SSID, String.valueOf(u.getSsid())).commit()
//                                    && pref.edit().putString(Config.USER, String.valueOf(u.getUsername())).commit()
//                                    && pref.edit().putString(Config.EMAIL, email).commit()) {
//
//                                }
//
//                            } else {
//                                //Detele save login state
//                                SharedPreferences.Editor editor = pref.edit();
//                                editor.remove(Config.SSID);
//                                editor.remove(Config.USER);
//                                editor.remove(Config.EMAIL);
//                                editor.apply();
//                            }

                            if (
                                    pref.edit().putString(Config.SSID, String.valueOf(u.getSsid())).commit()
                                            && pref.edit().putString(Config.USER, String.valueOf(u.getUsername())).commit()
                                            && pref.edit().putString(Config.EMAIL, email).commit()) {
                                if (Integer.parseInt(u.getRoles()) == 0) {
                                    //student
                                    Intent student_intent = new Intent(LoginActivity.this, StudentActivity.class);
                                    student_intent.putExtra("username", u.getUsername());
                                    startActivity(student_intent);
                                    finish();
                                }

                                if (Integer.parseInt(u.getRoles()) == 1) {
                                    //admin - DOING
                                }
                                login_btn.setClickable(true);
                                login_btn.setText("Login");
                            }
                        }

                        if (response.code() == 404) {
                            login_btn.setClickable(true);
                            login_btn.setText("Login");
                            Toast.makeText(getApplicationContext(), "Account is not existed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        login_btn.setClickable(true);
                        login_btn.setText("Login");
                        Toast.makeText(getApplicationContext(), "Unknow error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}