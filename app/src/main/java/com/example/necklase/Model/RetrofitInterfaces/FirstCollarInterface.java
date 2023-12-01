package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.FirstCollarModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface FirstCollarInterface {
    @GET("firstDisp/{id}")
    Call<FirstCollarModel> getData(@Body FirstCollarModel firstCollarModel, @Body String id);
}
