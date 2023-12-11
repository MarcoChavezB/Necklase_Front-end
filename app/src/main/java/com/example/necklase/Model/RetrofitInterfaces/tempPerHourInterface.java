package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Get.tempPerHourModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface tempPerHourInterface {
    @GET("getTempPerHour")
    Call<List<tempPerHourModel>> getGraph(@Query("deviceCode") String deviceCode);
}
