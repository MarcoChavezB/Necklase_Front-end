package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Post.LoginPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    @POST("login")
    Call<LoginPostModel> login(@Body LoginPostModel loginPostModelz);
}
