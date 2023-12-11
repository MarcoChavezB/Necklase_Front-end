package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.BoleanModel;
import com.example.necklase.Model.Get.Device;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DispositivosInterface {
    @GET("user/{id}")
    Call<List<Device>> getDevices(@Path("id") String id);

    @GET("IsDeviceLinked")
    Call<BoleanModel> getDevice(@Query("deviceCode") String code);

}
