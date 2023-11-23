package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Device;
import com.example.necklase.Model.Post.LoginPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DispositivosInterface {
    @GET("dispositivos")
    Call<Device> getDevices(@Body int id);

}
