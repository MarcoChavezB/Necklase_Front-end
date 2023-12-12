package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.FirstdModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FirstdInterface {
    @GET("firstDisp/{id}")
    Call<FirstdModel> getDevice(@Path("id") String idUser);
}
