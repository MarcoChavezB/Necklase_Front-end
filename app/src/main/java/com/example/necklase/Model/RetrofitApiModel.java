package com.example.necklase.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.necklase.Model.Interceptor.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiModel {

    private static final String API_BASE_URL = "http://18.223.154.5/api/";
    private String token;
    private Context context;

    public RetrofitApiModel(Context context) {
        this.context = context;
        SharedPreferences prefs = context.getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        this.token = prefs.getString("token", null);
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit provideRetrofitoken() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(token))
                .build();

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
