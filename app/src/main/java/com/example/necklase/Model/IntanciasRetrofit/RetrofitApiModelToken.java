package com.example.necklase.Model.IntanciasRetrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.necklase.Model.Interceptor.AuthInterceptor;
import com.example.necklase.View.activity_bienvenida;
import com.example.necklase.View.login_view;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiModelToken {

    private static final String API_BASE_URL = "http://18.223.154.5/api/";
    private String token;

    public RetrofitApiModelToken() {
        ViewModelTokenIns vs = ViewModelTokenIns.getinstance();
        this.token = vs.token();
    }
    public Retrofit provideRetrofit() {
        ViewModelTokenIns tokenins = ViewModelTokenIns.getinstance();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(tokenins))
                .build();

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
