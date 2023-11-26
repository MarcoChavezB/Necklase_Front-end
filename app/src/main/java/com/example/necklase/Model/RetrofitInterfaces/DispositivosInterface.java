package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.Device;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DispositivosInterface {
    @GET("user/{id}")
    Call<List<Device>> getDevices(@Path("id") String id);

}
