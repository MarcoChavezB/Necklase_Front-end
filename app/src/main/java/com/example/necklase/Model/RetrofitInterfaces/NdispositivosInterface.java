package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Post.NdispositivosModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NdispositivosInterface {
    @POST("dispositivos/{id}")
    Call<NdispositivosModel> dispositivos(@Path("id") String id, @Body NdispositivosModel ndispositivosModel);
}
