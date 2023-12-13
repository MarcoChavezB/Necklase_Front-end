package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Get.SoundModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SoundInterface {
    @GET("getSoundValue")
    Call<SoundModel> getSound(@Query("deviceCode") String device);
}
