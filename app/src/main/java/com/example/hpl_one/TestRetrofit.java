package com.example.hpl_one;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hpl_one.Modules.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class TestRetrofit extends AppCompatActivity {
    private Button call;
    private TextView show;
    public static final String baseURL = "http://192.168.0.2:8080/";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_api);
    }

    public interface CallAPI {
        @GET("abc/")
        Call<String> test();

        @POST("test/")
        Call<String> testPost();

        @FormUrlEncoded
        @POST("api/get_ques/")
        Call<Question> singleQues(@Field("email") String email, @Field("level") String level, @Field("ssid") String ssid);
    }

    @Override
    protected void onStart() {
        super.onStart();
        call = findViewById(R.id.call);
        show = findViewById(R.id.display);

        OkHttpClient builder = new OkHttpClient.Builder()
                .build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallAPI x = retrofit.create(CallAPI.class);
                Call<Question> g = x.singleQues("test1@gmail.com", "1", "wG6m+GMrZt57vRc9YMa4xQ==");
                g.enqueue(new Callback<Question>() {
                    @Override
                    public void onResponse(Call<Question> call, Response<Question> response) {
                        if (response.isSuccessful()) {
                            show.setText(response.body().getQues()+"\nA. "+response.body().getAnswer_a());
                        }
                    }

                    @Override
                    public void onFailure(Call<Question> call, Throwable t) {

                    }
                });
            }
        });
    }
}