package com.example.hpl_one.Services;

import com.example.hpl_one.Modules.Question;
import com.example.hpl_one.Modules.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIConfig {
    //Login
    @FormUrlEncoded
    @POST("api/login/")
    Call<User> login(
            @Field("email") String email,
            @Field("password") String pass
    );

    //Create user
    @FormUrlEncoded
    @POST("api/new_user/")
    Call createUser(
            @Field("ssid") String ssid,
            @Field("email") String email,
            @Field("username") String username,
            @Field("dob") String dob,
            @Field("sex") String sex,
            @Field("roles") String roles
    );

    //Get question
    @FormUrlEncoded
    @POST("api/get_ques/")
    Call<Question> getQues(
            @Field("ssid") String ssid,
            @Field("email") String email,
            @Field("level") String level
    );

    //Logout
    @FormUrlEncoded
    @POST("api/logout/")
    Call logout(@Field("ssid") String ssid, @Field("email") String email);
}
