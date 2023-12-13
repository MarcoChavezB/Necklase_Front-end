package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.LocationModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationInterface {
    @GET("getLocation")
    Call<LocationModel> getLocation(@Query("device") String device);
}
