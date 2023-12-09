package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Get.ForeModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForeInterface {
    @GET("getForecast")
    Call<ForeModel> getData(@Query("coords") String coords);
}
