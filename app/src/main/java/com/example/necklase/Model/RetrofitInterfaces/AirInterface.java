package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Get.AirModel;
import com.example.necklase.Model.Get.TempModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirInterface {
    @GET("getAirQuality")
    Call<AirModel> getData(@Query("deviceCode") String deviceCode);
}
