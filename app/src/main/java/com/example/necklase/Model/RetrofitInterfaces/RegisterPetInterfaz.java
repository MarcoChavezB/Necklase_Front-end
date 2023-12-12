package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.Post.RegisterPetModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterPetInterfaz {
    @POST("registerPetYDev")
    Call<MessageModel> register(@Body RegisterPetModel registerPostModel);
}
