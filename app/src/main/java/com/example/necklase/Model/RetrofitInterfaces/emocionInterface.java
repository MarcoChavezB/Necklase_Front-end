package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.caloriasModel;
import com.example.necklase.Model.Get.emocionalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface emocionInterface {
    @GET("getDogData")
    Call<emocionalModel> getData(@Query("deviceCode") String deviceCode);
}
