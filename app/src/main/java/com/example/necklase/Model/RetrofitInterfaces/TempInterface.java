package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.HumModel;
import com.example.necklase.Model.Get.TempModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TempInterface {
    @GET("getTempData")
    Call<TempModel> getData(@Query("deviceCode") String deviceCode);
}
