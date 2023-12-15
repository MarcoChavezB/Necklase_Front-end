package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Get.LedModel;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LedInterfaz {
    @POST("TurnOnLed/{string}")
    Call<LedModel> turnled(@Path("string") String string);
    @POST("TurnOnBuzzer/{string}")
    Call<LedModel> turnbuzzer(@Path("string") String string);
}
