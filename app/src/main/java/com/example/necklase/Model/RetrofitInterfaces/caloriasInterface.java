package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.caloriasModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface caloriasInterface {
    @GET("getCaloriesBurned")
    Call<caloriasModel> getData(@Query("deviceCode") String deviceCode, @Query("peso") String peso);
}
