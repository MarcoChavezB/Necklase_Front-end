package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Post.RegisterPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RegisterInterface {
    @POST("register")
    Call<RegisterPostModel> register(@Body RegisterPostModel registerPostModel);
}
