package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.HumInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HumManagment {
    private final HumInterface humInterface;

    public HumManagment(Retrofit retrofit) {
        this.humInterface = retrofit.create(HumInterface.class);
    }

    public void getData(String deviceCode, Callback<HumModel> callback) {
        Call<HumModel> call = humInterface.getData(deviceCode);
        call.enqueue(callback);
    }
}

