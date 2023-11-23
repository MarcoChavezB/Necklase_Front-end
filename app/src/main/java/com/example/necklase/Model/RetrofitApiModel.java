package com.example.necklase.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiModel {
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://18.223.154.5/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
