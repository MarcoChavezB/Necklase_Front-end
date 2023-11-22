package com.example.necklase.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private String BASE_URL;

    public RetrofitProvider(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
