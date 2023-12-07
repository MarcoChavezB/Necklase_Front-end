package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.AirInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AirManagment {
    private final AirInterface airInterface;

    public AirManagment(Retrofit retro){
        this.airInterface = retro.create(AirInterface.class);
    }

    public void getData(String device, Callback<AirModel> callback){
        Call<AirModel> call = airInterface.getData(device);
        call.enqueue(callback);
    }
}
