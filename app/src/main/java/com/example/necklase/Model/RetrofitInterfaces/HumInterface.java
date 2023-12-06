package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.HumModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HumInterface {
    @GET("getHumData")
    Call<HumModel> getData(@Query("deviceCode") String deviceCode);
}
