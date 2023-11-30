package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Get.DeviceUserModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DevicesUserInterface {
    @GET("user/{id}")
    Call<List<DeviceUserModel>> getDevices(@Path("id") DeviceUserModel id);
}
